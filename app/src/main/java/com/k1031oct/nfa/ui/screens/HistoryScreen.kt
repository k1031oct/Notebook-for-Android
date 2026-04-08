package com.k1031oct.nfa.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.k1031oct.nfa.data.models.HistoryItem
import com.k1031oct.nfa.ui.viewmodels.NoteViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HistoryScreen(viewModel: NoteViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Activity History",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        ContributionGraph(uiState.history)

        Spacer(modifier = Modifier.height(24.dp))

        if (uiState.history.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No activity yet", color = Color.Gray)
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(uiState.history) { item ->
                    HistoryItemCard(item)
                }
            }
        }
    }
}

@Composable
fun HistoryItemCard(item: HistoryItem) {
    val sdf = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault())
    val dateStr = sdf.format(Date(item.timestamp))

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = when(item.action) {
                            "Created" -> Color(0xFF4CAF50).copy(alpha = 0.1f)
                            "Updated" -> Color(0xFF2196F3).copy(alpha = 0.1f)
                            "Deleted" -> Color(0xFFF44336).copy(alpha = 0.1f)
                            else -> Color.Gray.copy(alpha = 0.1f)
                        },
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = when(item.action) {
                        "Created" -> Icons.Default.CheckCircle
                        "Updated" -> Icons.Default.Edit
                        else -> Icons.Default.Info
                    },
                    contentDescription = null,
                    tint = when(item.action) {
                        "Created" -> Color(0xFF4CAF50)
                        "Updated" -> Color(0xFF2196F3)
                        "Deleted" -> Color(0xFFF44336)
                        else -> Color.Gray
                    },
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${item.action}: ${item.noteTitle}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFF333333)
                )
                Text(
                    text = dateStr,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            
            Text(
                text = item.status,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = if (item.status == "Success") Color(0xFF4CAF50) else Color.Red
            )
        }
    }
}

@Composable
fun ContributionGraph(history: List<HistoryItem>) {
    val calendar = Calendar.getInstance()
    val today = calendar.timeInMillis
    val oneDay = 24 * 60 * 60 * 1000L
    
    Column {
        Text("Activity Map", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.fillMaxWidth()) {
            for (i in 17 downTo 0) { // Show 18 days for simplicity on screen
                val dayStart = today - (i * oneDay)
                val dayEnd = dayStart + oneDay
                val count = history.count { it.timestamp in dayStart until dayEnd }
                
                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .background(
                            color = when {
                                count >= 5 -> Color(0xFF216E39)
                                count >= 3 -> Color(0xFF30A14E)
                                count >= 1 -> Color(0xFF9BE9A8)
                                else -> Color.LightGray.copy(alpha = 0.3f)
                            },
                            shape = RoundedCornerShape(2.dp)
                        )
                )
            }
        }
    }
}
