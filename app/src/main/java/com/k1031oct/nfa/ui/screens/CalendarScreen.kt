package com.k1031oct.nfa.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ViewModule
import androidx.compose.material.icons.filled.ViewWeek
import androidx.compose.material.icons.filled.ViewDay
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.k1031oct.nfa.data.models.Note
import com.k1031oct.nfa.ui.viewmodels.NoteViewModel
import java.util.*

import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import com.k1031oct.nfa.util.HolidayUtil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    viewModel: NoteViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentYearMonth = YearMonth.parse(uiState.currentMonth)
    val daysInMonth = currentYearMonth.lengthOfMonth()
    val firstDayOfMonth = currentYearMonth.atDay(1)
    val dayOfWeekOffset = firstDayOfMonth.dayOfWeek.value % 7 // 0=Sun, 1=Mon, ..., 6=Sat

    val plans = uiState.notes.filter { it.date == uiState.selectedDate }
        .sortedBy { it.time ?: "23:59" }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // View Type Toggle
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            listOf("Month", "Week", "Day").forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = 3),
                    onClick = { viewModel.changeCalendarViewType(label) },
                    selected = uiState.calendarViewType == label,
                    colors = SegmentedButtonDefaults.colors(
                        activeContainerColor = Color(0xFFFF5E62).copy(alpha = 0.1f),
                        activeContentColor = Color(0xFFFF5E62)
                    )
                ) {
                    Text(label, fontSize = 12.sp)
                }
            }
        }

        // Calendar Header
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { viewModel.previousMonth() }) {
                Icon(Icons.Default.ChevronLeft, contentDescription = "Prev")
            }
            Text(
                text = "${currentYearMonth.month.getDisplayName(TextStyle.FULL, Locale.getDefault())} ${currentYearMonth.year}",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF333333)
            )
            IconButton(onClick = { viewModel.nextMonth() }) {
                Icon(Icons.Default.ChevronRight, contentDescription = "Next")
            }
        }

        // Day of Week Header
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
            listOf("S", "M", "T", "W", "T", "F", "S").forEach { day ->
                Text(
                    text = day,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = if (day == "S") Color(0xFFFF5E62) else Color.Gray,
                    fontSize = 12.sp
                )
            }
        }

        // Calendar Content with Swipe Support
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .pointerInput(uiState.currentMonth) {
                    detectHorizontalDragGestures { _, dragAmount ->
                        if (dragAmount > 50) {
                            viewModel.previousMonth()
                        } else if (dragAmount < -50) {
                            viewModel.nextMonth()
                        }
                    }
                }
        ) {
            if (uiState.calendarViewType == "Month") {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(7),
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(dayOfWeekOffset) { Spacer(Modifier) }
                    items(daysInMonth) { dayIndex ->
                        val day = dayIndex + 1
                        val dateStr = currentYearMonth.atDay(day).toString()
                        CalendarDayItem(day, dateStr, uiState, viewModel)
                    }
                }
            } else {
                // Placeholder for Week/Day view
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("${uiState.calendarViewType} view coming soon", color = Color.Gray)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = Color.LightGray.copy(alpha = 0.5f))
        Spacer(modifier = Modifier.height(16.dp))

        // Selected Date Plans
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Plans for ${uiState.selectedDate}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xFF333333)
            )
        }

        if (plans.isEmpty()) {
            Box(Modifier.weight(1f).fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text("No plans for this day", color = Color.Gray, fontSize = 14.sp)
            }
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(plans) { plan ->
                    PlanItem(plan, onEdit = { viewModel.selectNote(it) })
                }
            }
        }

        // Add Plan Button
        Button(
            onClick = { viewModel.selectNote(Note(date = uiState.selectedDate)) },
            modifier = Modifier.fillMaxWidth().height(56.dp).padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5E62)),
            shape = RoundedCornerShape(16.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Add Plan", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun CalendarDayItem(day: Int, dateStr: String, uiState: com.k1031oct.nfa.ui.states.NoteUiState, viewModel: NoteViewModel) {
    val isSelected = uiState.selectedDate == dateStr
    val isToday = LocalDate.now().toString() == dateStr
    val hasPlans = uiState.notes.any { it.date == dateStr }
    val holidayName = HolidayUtil.getHoliday(LocalDate.parse(dateStr), uiState.holidayCountry)
    val isHoliday = holidayName != null

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .background(
                color = when {
                    isSelected -> Color(0xFFFF5E62)
                    isToday -> Color(0xFFFF5E62).copy(alpha = 0.1f)
                    else -> Color.Transparent
                },
                shape = CircleShape
            )
            .clickable { viewModel.selectDate(dateStr) },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = day.toString(),
                color = when {
                    isSelected -> Color.White
                    isHoliday -> Color(0xFFFF5E62)
                    isToday -> Color(0xFFFF5E62)
                    else -> Color.Black
                },
                fontWeight = if (isSelected || isToday || isHoliday) FontWeight.Bold else FontWeight.Normal,
                fontSize = 14.sp
            )
            if (holidayName != null && !isSelected) {
                Text(
                    text = holidayName.take(4),
                    fontSize = 8.sp,
                    color = Color(0xFFFF5E62),
                    maxLines = 1
                )
            }
            if (hasPlans && !isSelected) {
                Box(
                    modifier = Modifier
                        .size(4.dp)
                        .background(
                            if (isHoliday) Color(0xFFFF5E62).copy(alpha = 0.5f) else Color(0xFF4389A2),
                            CircleShape
                        )
                )
            }
        }
    }
}

@Composable
fun PlanItem(plan: Note, onEdit: (Note) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onEdit(plan) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(Color(0xFFFF5E62), CircleShape)
            )
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = plan.title, 
                    fontWeight = FontWeight.Bold, 
                    fontSize = 16.sp,
                    color = Color(0xFF333333)
                )
                if (plan.time != null) {
                    Text(
                        text = plan.time,
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.LightGray)
        }
    }
}
