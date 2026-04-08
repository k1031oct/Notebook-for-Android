package com.k1031oct.nfa.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GamesScreen() {
    var activeGame by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (activeGame == null) {
            Text(
                text = "Mini Games",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            GameCategoryItem(
                title = "Tic-Tac-Toe",
                subtitle = "Classic 3x3 game",
                icon = Icons.Default.Games,
                color = Color(0xFFFF5E62),
                onClick = { activeGame = "TicTacToe" }
            )
            Spacer(Modifier.height(16.dp))
            GameCategoryItem(
                title = "Amidakuji",
                subtitle = "Ghost Leg lottery",
                icon = Icons.Default.ColorLens,
                color = Color(0xFF4389A2),
                onClick = { activeGame = "Amidakuji" }
            )
            Spacer(Modifier.height(16.dp))
            GameCategoryItem(
                title = "Lottery",
                subtitle = "Draw a random winner",
                icon = Icons.Default.Casino,
                color = Color(0xFF5C258D),
                onClick = { activeGame = "Lottery" }
            )
        } else {
            // Game UI Header
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = { activeGame = null }) {
                    Text("← Back to Hub", color = Color.Gray)
                }
                Spacer(Modifier.weight(1f))
            }

            when (activeGame) {
                "TicTacToe" -> TicTacToe()
                "Amidakuji" -> AmidakujiScreen()
                "Lottery" -> LotteryScreen()
            }
        }
    }
}

@Composable
fun GameCategoryItem(title: String, subtitle: String, icon: androidx.compose.ui.graphics.vector.ImageVector, color: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().height(100.dp).clickable(onClick = onClick),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f))
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(modifier = Modifier.size(56.dp), shape = RoundedCornerShape(16.dp), color = color) {
                Icon(icon, null, tint = Color.White, modifier = Modifier.padding(12.dp))
            }
            Spacer(Modifier.width(20.dp))
            Column {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = color)
                Text(subtitle, fontSize = 12.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun TicTacToe() {
    var board by remember { mutableStateOf(List(9) { "" }) }
    var xIsNext by remember { mutableStateOf(true) }
    val winner = calculateWinner(board)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = if (winner != null) "$winner Wins!" else if (board.all { it.isNotEmpty() }) "Draw!" else if (xIsNext) "X's Turn" else "O's Turn",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            for (r in 0..2) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    for (c in 0..2) {
                        val index = r * 3 + c
                        TicTacToeSquare(value = board[index], onClick = {
                            if (board[index].isEmpty() && winner == null) {
                                board = board.toMutableList().apply { this[index] = if (xIsNext) "X" else "O" }
                                xIsNext = !xIsNext
                            }
                        })
                    }
                }
            }
        }

        Spacer(Modifier.height(32.dp))
        Button(
            onClick = { board = List(9) { "" }; xIsNext = true },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
        ) { Text("Reset Game") }
    }
}

@Composable
fun TicTacToeSquare(value: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.size(80.dp).clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = value,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = if (value == "X") Color(0xFFFF5E62) else Color(0xFF4389A2)
            )
        }
    }
}

fun calculateWinner(board: List<String>): String? {
    val lines = listOf(
        listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
        listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
        listOf(0, 4, 8), listOf(2, 4, 6)
    )
    for (line in lines) {
        if (board[line[0]].isNotEmpty() && board[line[0]] == board[line[1]] && board[line[0]] == board[line[2]]) {
            return board[line[0]]
        }
    }
    return null
}

@Composable
fun AmidakujiScreen() {
    var result by remember { mutableStateOf<Int?>(null) }
    val lines = 4
    val horizonStep = 5
    val seeds = remember { List(lines - 1) { List(horizonStep) { (0..1).random() } } }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
        Text("Ghost Leg (Amidakuji)", fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        
        Canvas(modifier = Modifier.size(200.dp, 200.dp)) {
            val width = size.width
            val height = size.height
            val spacing = width / (lines + 1)
            
            for (i in 1..lines) {
                val x = i * spacing
                drawLine(Color.Gray, start = androidx.compose.ui.geometry.Offset(x, 0f), end = androidx.compose.ui.geometry.Offset(x, height), strokeWidth = 4f)
            }
            
            seeds.forEachIndexed { i, steps ->
                val x1 = (i + 1) * spacing
                val x2 = (i + 2) * spacing
                steps.forEachIndexed { j, draw ->
                    if (draw == 1) {
                        val y = (j + 1) * (height / (horizonStep + 1))
                        drawLine(Color.Gray, start = androidx.compose.ui.geometry.Offset(x1, y), end = androidx.compose.ui.geometry.Offset(x2, y), strokeWidth = 4f)
                    }
                }
            }
        }

        Spacer(Modifier.height(24.dp))
        if (result != null) {
            Text("Result: Path ${result!! + 1}", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF4389A2))
        }
        
        Button(
            onClick = { result = (0 until lines).random() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4389A2))
        ) { Text("Draw Path") }
    }
}

@Composable
fun LotteryScreen() {
    var result by remember { mutableStateOf("Ready?") }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(result, fontSize = 48.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF5C258D))
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = {
                val winners = listOf("YES", "NO", "TRY AGAIN", "JACKPOT!", "LOSE")
                result = winners.random()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5C258D))
        ) { Text("Draw Now") }
    }
}
