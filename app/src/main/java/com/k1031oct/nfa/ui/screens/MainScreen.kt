package com.k1031oct.nfa.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.k1031oct.nfa.data.models.Note
import com.k1031oct.nfa.ui.viewmodels.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: NoteViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notebook for Android", fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { 
                        viewModel.signOut()
                    }) {
                        Icon(Icons.Default.Menu, contentDescription = "Sign Out")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF5F5F5)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.selectNote(Note()) },
                containerColor = Color(0xFFFF5E62),
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Note")
            }
        },
        containerColor = Color(0xFFF5F5F5),
        bottomBar = {
            NavigationBar(
                containerColor = Color.White
            ) {
                NavigationBarItem(
                    selected = uiState.activeSection == "memo",
                    onClick = { viewModel.showSection("memo") },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Memo") },
                    label = { Text("Memo") }
                )
                NavigationBarItem(
                    selected = uiState.activeSection == "calculator",
                    onClick = { viewModel.showSection("calculator") },
                    icon = { Icon(Icons.Default.Edit, contentDescription = "Calculator") },
                    label = { Text("Calc") }
                )
                NavigationBarItem(
                    selected = uiState.activeSection == "calendar",
                    onClick = { viewModel.showSection("calendar") },
                    icon = { Icon(Icons.Default.Settings, contentDescription = "Calendar") },
                    label = { Text("Plan") }
                )
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            when (uiState.activeSection) {
                "memo" -> {
                    Row(modifier = Modifier.fillMaxSize()) {
                        // Sidebar for Refills
                        RefillSidebar(
                            activeRefill = uiState.activeRefill,
                            onRefillSelected = { viewModel.changeRefill(it) }
                        )

                        // Main Content with Refill Effect
                        Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                            // Grid overlay for Grid Refill
                            if (uiState.activeRefill == "grid") {
                                GridBackground()
                            }
                            
                    NoteList(
                                notes = uiState.notes,
                                activeRefill = uiState.activeRefill,
                                onNoteClick = { viewModel.selectNote(it) },
                                onDeleteClick = { viewModel.deleteNote(it.id) },
                                onToggleComplete = { viewModel.toggleNoteCompletion(it) }
                            )
                        }
                    }
                }
                "calculator" -> {
                    CalculatorScreen(
                        display = uiState.calculatorDisplay,
                        onAction = { viewModel.onCalculatorAction(it) }
                    )
                }
                "calendar" -> {
                    CalendarScreen(viewModel = viewModel)
                }
                else -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Section: ${uiState.activeSection}")
                    }
                }
            }
        }

        // Editor Overlay
        if (uiState.isEditing && uiState.selectedNote != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            ) {
                EditorScreen(
                    note = uiState.selectedNote!!,
                    viewModel = viewModel,
                    onBack = { viewModel.selectNote(null) }
                )
            }
        }
    }
}

@Composable
fun GridBackground() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val gridSize = 30.dp.toPx()
        val color = Color.LightGray.copy(alpha = 0.5f)
        for (x in 0..size.width.toInt() step gridSize.toInt()) {
            drawLine(
                color = color,
                start = androidx.compose.ui.geometry.Offset(x.toFloat(), 0f),
                end = androidx.compose.ui.geometry.Offset(x.toFloat(), size.height),
                strokeWidth = 1f
            )
        }
        for (y in 0..size.height.toInt() step gridSize.toInt()) {
            drawLine(
                color = color,
                start = androidx.compose.ui.geometry.Offset(0f, y.toFloat()),
                end = androidx.compose.ui.geometry.Offset(size.width, y.toFloat()),
                strokeWidth = 1f
            )
        }
    }
}

@Composable
fun RefillSidebar(
    activeRefill: String,
    onRefillSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .width(80.dp)
            .fillMaxHeight()
            .background(Color(0xFFE0E0E0))
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RefillItem("Standard", activeRefill == "standard") { onRefillSelected("standard") }
        Spacer(modifier = Modifier.height(16.dp))
        RefillItem("Grid", activeRefill == "grid") { onRefillSelected("grid") }
        Spacer(modifier = Modifier.height(16.dp))
        RefillItem("ToDo", activeRefill == "todo") { onRefillSelected("todo") }
    }
}

@Composable
fun RefillItem(label: String, isActive: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(60.dp)
            .background(
                color = if (isActive) Color(0xFFFF5E62) else Color.White,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label.take(1),
            color = if (isActive) Color.White else Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun NoteList(
    notes: List<Note>,
    activeRefill: String,
    onNoteClick: (Note) -> Unit,
    onDeleteClick: (Note) -> Unit,
    onToggleComplete: (Note) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(notes) { note ->
            NoteCard(note, activeRefill, onNoteClick, onDeleteClick, onToggleComplete)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun NoteCard(
    note: Note,
    activeRefill: String,
    onNoteClick: (Note) -> Unit,
    onDeleteClick: (Note) -> Unit,
    onToggleComplete: (Note) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNoteClick(note) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (activeRefill == "todo") {
                Checkbox(
                    checked = note.isCompleted,
                    onCheckedChange = { onToggleComplete(note) },
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            
            Column(modifier = Modifier.weight(1f).fillMaxHeight()) {
                Text(text = note.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = note.content, fontSize = 14.sp, color = Color.Gray, maxLines = 1)
            }
            IconButton(onClick = { onDeleteClick(note) }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.LightGray)
            }
        }
    }
}
