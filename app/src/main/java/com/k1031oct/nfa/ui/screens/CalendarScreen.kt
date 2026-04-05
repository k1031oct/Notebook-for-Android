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

@Composable
fun CalendarScreen(
    viewModel: NoteViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val plans = uiState.notes.filter { it.date == uiState.selectedDate }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "April 2026", // 簡易化のため固定（本来は動的に作成）
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Day of Week Header
        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
            listOf("S", "M", "T", "W", "T", "F", "S").forEach { day ->
                Text(
                    text = day,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
            }
        }

        // Calendar Grid (30 days for April)
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.height(280.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // First 3 days are empty (April 2026 starts on Wednesday)
            items(3) { Spacer(Modifier) }
            
            items(30) { dayIndex ->
                val day = dayIndex + 1
                val dateStr = "2026-04-${String.format("%02d", day)}"
                val isSelected = uiState.selectedDate == dateStr
                val hasPlans = uiState.notes.any { it.date == dateStr }

                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .background(
                            color = if (isSelected) Color(0xFFFF5E62) else Color.Transparent,
                            shape = CircleShape
                        )
                        .clickable { viewModel.selectDate(dateStr) },
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = day.toString(),
                            color = if (isSelected) Color.White else Color.Black,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                        if (hasPlans && !isSelected) {
                            Box(
                                modifier = Modifier
                                    .size(4.dp)
                                    .background(Color(0xFFFF5E62), CircleShape)
                            )
                        }
                    }
                }
            }
        }

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // Selected Date Plans
        Text(
            text = "Plans for ${uiState.selectedDate}",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        if (plans.isEmpty()) {
            Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Text("No plans for this day", color = Color.Gray)
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
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5E62))
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Add Plan")
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
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(Color(0xFFFF5E62), CircleShape)
            )
            Spacer(Modifier.width(12.dp))
            Text(text = plan.title, fontWeight = FontWeight.Bold, maxLines = 1)
        }
    }
}
