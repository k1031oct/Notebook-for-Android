package com.k1031oct.nfa.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.k1031oct.nfa.data.models.Note
import com.k1031oct.nfa.ui.viewmodels.NoteViewModel
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen(
    note: Note,
    viewModel: NoteViewModel,
    onBack: () -> Unit
) {
    var title by remember { mutableStateOf(note.title) }
    var content by remember { mutableStateOf(note.content) }
    var date by remember { mutableStateOf(note.date ?: "") }
    var time by remember { mutableStateOf(note.time ?: "") }
    var selectedFolderId by remember { mutableStateOf(note.folderId) }
    var showFolderMenu by remember { mutableStateOf(false) }
    var imageUrl by remember { mutableStateOf(note.imageUrl ?: "") }
    var tagsInput by remember { mutableStateOf(note.tags.joinToString(", ")) }
    var isPreviewMode by remember { mutableStateOf(false) }
    var showReminderDialog by remember { mutableStateOf(false) }
    var subtasks by remember { mutableStateOf(note.subtasks) }
    var newSubtaskTitle by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (isPreviewMode) "Preview" else "Edit Note", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { isPreviewMode = !isPreviewMode }) {
                        Icon(
                            if (isPreviewMode) Icons.Default.Edit else Icons.Default.Visibility,
                            contentDescription = "Toggle Preview"
                        )
                    }
                    IconButton(onClick = { showReminderDialog = true }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Reminder")
                    }
                    Button(
                        onClick = {
                            viewModel.saveNote(note.copy(
                                title = title,
                                content = content,
                                date = if (date.isEmpty()) null else date,
                                time = if (time.isEmpty()) null else time,
                                folderId = selectedFolderId,
                                imageUrl = if (imageUrl.isEmpty()) null else imageUrl,
                                tags = tagsInput.split(",").map { it.trim() }.filter { it.isNotEmpty() },
                                subtasks = subtasks
                            ))
                            onBack()
                        },
                        modifier = Modifier.padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5E62)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Save")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        containerColor = Color(0xFFFBFBFB)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Folder Selection Card
            Box {
                OutlinedCard(
                    onClick = { showFolderMenu = true },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.outlinedCardColors(containerColor = Color.White)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.Folder, contentDescription = null, tint = Color(0xFFFF5E62))
                        Spacer(Modifier.width(12.dp))
                        Text(
                            text = uiState.folders.find { it.id == selectedFolderId }?.name ?: "No Folder",
                            modifier = Modifier.weight(1f)
                        )
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                }
                DropdownMenu(expanded = showFolderMenu, onDismissRequest = { showFolderMenu = false }) {
                    DropdownMenuItem(
                        text = { Text("No Folder") },
                        onClick = { selectedFolderId = null; showFolderMenu = false },
                        leadingIcon = { Icon(Icons.Default.Folder, null, tint = Color.Gray) }
                    )
                    uiState.folders.forEach { folder ->
                        DropdownMenuItem(
                            text = { Text(folder.name) },
                            onClick = { selectedFolderId = folder.id; showFolderMenu = false },
                            leadingIcon = { Icon(Icons.Default.Folder, null, tint = Color(0xFFFF5E62)) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Image URL & Preview
            OutlinedTextField(
                value = imageUrl,
                onValueChange = { imageUrl = it },
                label = { Text("Image URL") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            if (imageUrl.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.LightGray, RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tags Field
            OutlinedTextField(
                value = tagsInput,
                onValueChange = { tagsInput = it },
                label = { Text("Tags (comma separated)") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Title Field
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Date & Time Row
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = date,
                    onValueChange = { date = it },
                    label = { Text("Date (YYYY-MM-DD)") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(12.dp))
                OutlinedTextField(
                    value = time,
                    onValueChange = { time = it },
                    label = { Text("Time (HH:mm)") },
                    modifier = Modifier.weight(0.7f),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Content Field or Preview
            if (isPreviewMode) {
                MarkdownPreview(content)
            } else {
                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    label = { Text("Content") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 150.dp),
                    shape = RoundedCornerShape(12.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Subtasks Section
            Text("Subtasks", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))

            subtasks.forEach { subtask ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        subtasks = subtasks.map { if (it.id == subtask.id) it.copy(isCompleted = !it.isCompleted) else it }
                    }) {
                        Icon(
                            if (subtask.isCompleted) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                            contentDescription = null,
                            tint = if (subtask.isCompleted) Color(0xFFFF5E62) else Color.Gray
                        )
                    }
                    Text(
                        text = subtask.title,
                        modifier = Modifier.weight(1f),
                        textDecoration = if (subtask.isCompleted) androidx.compose.ui.text.style.TextDecoration.LineThrough else null,
                        color = if (subtask.isCompleted) Color.Gray else Color.Black
                    )
                    IconButton(onClick = {
                        subtasks = subtasks.filter { it.id != subtask.id }
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete Subtask", tint = Color.LightGray)
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = newSubtaskTitle,
                    onValueChange = { newSubtaskTitle = it },
                    label = { Text("Add subtask") },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
                Spacer(Modifier.width(8.dp))
                IconButton(
                    onClick = {
                        if (newSubtaskTitle.isNotBlank()) {
                            subtasks = subtasks + com.k1031oct.nfa.data.models.SubTask(title = newSubtaskTitle)
                            newSubtaskTitle = ""
                        }
                    },
                    colors = IconButtonDefaults.iconButtonColors(containerColor = Color(0xFFFF5E62).copy(alpha = 0.1f))
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add", tint = Color(0xFFFF5E62))
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        if (showReminderDialog) {
            val context = androidx.compose.ui.platform.LocalContext.current
            AlertDialog(
                onDismissRequest = { showReminderDialog = false },
                title = { Text("Set Reminder") },
                text = {
                    Column {
                        Text("When should we remind you?")
                        Spacer(Modifier.height(8.dp))
                        listOf(1, 10, 60).forEach { mins ->
                            Button(
                                onClick = {
                                    com.k1031oct.nfa.util.NotificationHelper.scheduleReminder(
                                        context = context,
                                        delayMs = mins * 60 * 1000L,
                                        title = "Reminder: $title",
                                        content = content.take(50)
                                    )
                                    showReminderDialog = false
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5E62).copy(alpha = 0.1f), contentColor = Color(0xFFFF5E62))
                            ) {
                                Text("In $mins minutes")
                            }
                            Spacer(Modifier.height(4.dp))
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = { showReminderDialog = false }) { Text("Cancel") }
                }
            )
        }
    }
    }
}

@Composable
fun MarkdownPreview(content: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(8.dp)
    ) {
        content.split("\n").forEach { line ->
            when {
                line.startsWith("# ") -> Text(line.substring(2), fontSize = 24.sp, fontWeight = FontWeight.Bold)
                line.startsWith("## ") -> Text(line.substring(3), fontSize = 20.sp, fontWeight = FontWeight.Bold)
                line.startsWith("- ") -> Text("• ${line.substring(2)}", fontSize = 16.sp)
                else -> Text(line, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}
