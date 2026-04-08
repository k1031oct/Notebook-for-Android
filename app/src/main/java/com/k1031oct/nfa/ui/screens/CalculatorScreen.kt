package com.k1031oct.nfa.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.k1031oct.nfa.ui.viewmodels.NoteViewModel

@Composable
fun CalculatorScreen(
    display: String,
    onAction: (String) -> Unit,
    onSaveToNote: () -> Unit
) {
    val buttons = listOf(
        "7", "8", "9", "/",
        "4", "5", "6", "*",
        "1", "2", "3", "-",
        "C", "0", "=", "+"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Calculator",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        // Display area with Save Button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .height(100.dp),
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.large
            ) {
                Box(
                    contentAlignment = Alignment.CenterEnd,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = display,
                        style = MaterialTheme.typography.displaySmall,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.End,
                        maxLines = 1
                    )
                }
            }

            Button(
                onClick = onSaveToNote,
                modifier = Modifier
                    .height(100.dp)
                    .width(72.dp),
                shape = MaterialTheme.shapes.large,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5E62)),
                contentPadding = PaddingValues(0.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Text("Save", fontSize = 10.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Grid buttons
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(buttons) { btn ->
                CalculatorButton(
                    text = btn,
                    onClick = { onAction(btn) },
                    containerColor = when (btn) {
                        "C" -> MaterialTheme.colorScheme.errorContainer
                        "=", "/", "*", "-", "+" -> MaterialTheme.colorScheme.primaryContainer
                        else -> MaterialTheme.colorScheme.secondaryContainer
                    }
                )
            }
        }
    }
}

@Composable
fun CalculatorButton(
    text: String,
    onClick: () -> Unit,
    containerColor: Color
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .aspectRatio(1f)
            .fillMaxSize(),
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        shape = MaterialTheme.shapes.medium,
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = if (containerColor == MaterialTheme.colorScheme.errorContainer)
                MaterialTheme.colorScheme.onErrorContainer
            else if (containerColor == MaterialTheme.colorScheme.primaryContainer)
                MaterialTheme.colorScheme.onPrimaryContainer
            else
                MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}
