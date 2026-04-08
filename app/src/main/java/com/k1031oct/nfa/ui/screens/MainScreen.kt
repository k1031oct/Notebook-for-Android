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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.LocalOffer
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.CreateNewFolder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import androidx.hilt.navigation.compose.hiltViewModel
import com.k1031oct.nfa.data.models.Note
import com.k1031oct.nfa.data.models.Folder
import com.k1031oct.nfa.data.models.HistoryItem
import com.k1031oct.nfa.ui.viewmodels.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: NoteViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showCreateFolderDialog by remember { mutableStateOf(false) }
    var newFolderName by remember { mutableStateOf("") }
    var showQuickInputDialog by remember { mutableStateOf(false) }
    var quickInputContent by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Column(modifier = Modifier.background(Color.White)) {
                TopAppBar(
                    title = { 
                        Column {
                            Text("Notebook", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                            Text("Premium Personal Assistant", fontSize = 10.sp, color = Color.Gray)
                        }
                    },
                    actions = {
                        IconButton(onClick = { viewModel.showSection("user") }) {
                            if (uiState.currentUser?.photoUrl != null) {
                                AsyncImage(
                                    model = uiState.currentUser?.photoUrl,
                                    contentDescription = "User info",
                                    modifier = Modifier.size(32.dp).clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                Icon(Icons.Default.Person, contentDescription = "User info", tint = Color(0xFFFF5E62))
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
                )
                if (uiState.activeSection == "memo") {
                    OutlinedTextField(
                        value = uiState.searchQuery,
                        onValueChange = { viewModel.updateSearchQuery(it) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        placeholder = { Text("Search title, content, or tags...") },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        shape = RoundedCornerShape(12.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFF5E62),
                            unfocusedBorderColor = Color.LightGray
                        )
                    )
                }
            }
        },
        floatingActionButton = {
            if (uiState.activeSection == "memo" || uiState.activeSection == "calendar") {
                Column(horizontalAlignment = Alignment.End) {
                    SmallFloatingActionButton(
                        onClick = { showQuickInputDialog = true },
                        containerColor = Color(0xFF4389A2),
                        contentColor = Color.White,
                        shape = CircleShape
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = "Quick Note")
                    }
                    Spacer(Modifier.height(16.dp))
                    FloatingActionButton(
                        onClick = { viewModel.selectNote(Note(date = if (uiState.activeSection == "calendar") uiState.selectedDate else null)) },
                        containerColor = Color(0xFFFF5E62),
                        contentColor = Color.White,
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add Item")
                    }
                }
            }
        },
        containerColor = Color(0xFFFBFBFB),
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    selected = uiState.activeSection == "memo",
                    onClick = { viewModel.showSection("memo"); viewModel.changeRefill("standard") },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Notes") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFFFF5E62),
                        selectedTextColor = Color(0xFFFF5E62),
                        indicatorColor = Color(0xFFFF5E62).copy(alpha = 0.1f)
                    )
                )
                NavigationBarItem(
                    selected = uiState.activeSection == "memo" && uiState.activeRefill == "todo",
                    onClick = { viewModel.showSection("memo"); viewModel.changeRefill("todo") },
                    icon = { Icon(Icons.Default.Checklist, contentDescription = "Todo") },
                    label = { Text("Todo") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFFFF5E62),
                        selectedTextColor = Color(0xFFFF5E62),
                        indicatorColor = Color(0xFFFF5E62).copy(alpha = 0.1f)
                    )
                )
                NavigationBarItem(
                    selected = uiState.activeSection == "calendar",
                    onClick = { viewModel.showSection("calendar") },
                    icon = { Icon(Icons.Default.CalendarMonth, contentDescription = "Calendar") },
                    label = { Text("Plan") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFFFF5E62),
                        selectedTextColor = Color(0xFFFF5E62),
                        indicatorColor = Color(0xFFFF5E62).copy(alpha = 0.1f)
                    )
                )
                NavigationBarItem(
                    selected = uiState.activeSection == "history",
                    onClick = { viewModel.showSection("history") },
                    icon = { Icon(Icons.Default.History, contentDescription = "History") },
                    label = { Text("Map") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFFFF5E62),
                        selectedTextColor = Color(0xFFFF5E62),
                        indicatorColor = Color(0xFFFF5E62).copy(alpha = 0.1f)
                    )
                )
                NavigationBarItem(
                    selected = uiState.activeSection == "tools",
                    onClick = { viewModel.showSection("tools") },
                    icon = { Icon(Icons.Default.Calculate, contentDescription = "Tools") },
                    label = { Text("Tools") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFFFF5E62),
                        selectedTextColor = Color(0xFFFF5E62),
                        indicatorColor = Color(0xFFFF5E62).copy(alpha = 0.1f)
                    )
                )
            }
        }
    ) { padding ->
        if (showCreateFolderDialog) {
            AlertDialog(
                onDismissRequest = { showCreateFolderDialog = false },
                title = { Text("New Folder") },
                text = {
                    OutlinedTextField(
                        value = newFolderName,
                        onValueChange = { newFolderName = it },
                        label = { Text("Folder Name") }
                    )
                },
                confirmButton = {
                    TextButton(onClick = {
                        if (newFolderName.isNotBlank()) {
                            viewModel.createFolder(newFolderName)
                            newFolderName = ""
                        }
                        showCreateFolderDialog = false
                    }) { Text("Create") }
                },
                dismissButton = {
                    TextButton(onClick = { showCreateFolderDialog = false }) { Text("Cancel") }
                }
            )
        }

        if (showQuickInputDialog) {
            AlertDialog(
                onDismissRequest = { showQuickInputDialog = false },
                title = { Text("Quick Note") },
                text = {
                    OutlinedTextField(
                        value = quickInputContent,
                        onValueChange = { quickInputContent = it },
                        modifier = Modifier.fillMaxWidth().heightIn(min = 100.dp),
                        placeholder = { Text("Write something...") },
                        shape = RoundedCornerShape(12.dp)
                    )
                },
                confirmButton = {
                    Button(onClick = {
                        viewModel.quickAddNote(quickInputContent)
                        quickInputContent = ""
                        showQuickInputDialog = false
                    }) { Text("Save") }
                },
                dismissButton = {
                    TextButton(onClick = { showQuickInputDialog = false }) { Text("Cancel") }
                }
            )
        }

        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            when (uiState.activeSection) {
                "memo" -> {
                    Row(modifier = Modifier.fillMaxSize()) {
                        Sidebar(
                            activeFolderId = uiState.activeFolderId,
                            folders = uiState.folders,
                            onFolderSelected = { viewModel.selectFolder(it) },
                            onToggleFolderLock = { viewModel.toggleFolderLock(it) },
                            onDeleteFolder = { viewModel.deleteFolder(it) },
                            onCreateFolder = { showCreateFolderDialog = true }
                        )

                        Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                            if (uiState.activeRefill == "grid") {
                                GridBackground()
                            }
                            
                            val filteredNotes = uiState.notes.filter { note ->
                                (uiState.activeFolderId == null || note.folderId == uiState.activeFolderId) &&
                                (uiState.searchQuery.isEmpty() || 
                                 note.title.contains(uiState.searchQuery, ignoreCase = true) ||
                                 note.content.contains(uiState.searchQuery, ignoreCase = true) ||
                                 note.tags.any { it.contains(uiState.searchQuery, ignoreCase = true) })
                            }

                            androidx.compose.animation.AnimatedVisibility(
                                visible = uiState.notes.isNotEmpty(),
                                enter = androidx.compose.animation.fadeIn() + androidx.compose.animation.expandVertically(),
                                exit = androidx.compose.animation.fadeOut() + androidx.compose.animation.shrinkVertically()
                            ) {
                                NoteList(
                                    notes = filteredNotes,
                                    activeRefill = uiState.activeRefill,
                                    viewModel = viewModel,
                                    folders = uiState.folders
                                )
                            }

                            if (filteredNotes.isEmpty()) {
                                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(64.dp), tint = Color.LightGray)
                                        Text("No notes found", color = Color.Gray)
                                    }
                                }
                            }
                        }
                    }
                }
                "history" -> {
                    HistoryScreen(viewModel = viewModel)
                }
                "tools" -> {
                    var toolType by remember { mutableStateOf("Calculator") }
                    Column {
                        SingleChoiceSegmentedButtonRow(
                            modifier = Modifier.fillMaxWidth().padding(16.dp)
                        ) {
                            listOf("Calculator", "Games", "Apps").forEachIndexed { index, label ->
                                SegmentedButton(
                                    shape = SegmentedButtonDefaults.itemShape(index = index, count = 3),
                                    onClick = { toolType = label },
                                    selected = toolType == label,
                                    colors = SegmentedButtonDefaults.colors(
                                        activeContainerColor = Color(0xFFFF5E62).copy(alpha = 0.1f),
                                        activeContentColor = Color(0xFFFF5E62)
                                    )
                                ) { Text(label, fontSize = 10.sp) }
                            }
                        }
                        when (toolType) {
                            "Calculator" -> {
                                CalculatorScreen(
                                    display = uiState.calculatorDisplay,
                                    onAction = { viewModel.onCalculatorAction(it) },
                                    onSaveToNote = { viewModel.saveCalculatorResultAsNote() }
                                )
                            }
                            "Games" -> GamesScreen()
                            "Apps" -> AppShortcutsScreen()
                        }
                    }
                }
                "calendar" -> {
                    CalendarScreen(viewModel = viewModel)
                }
                "user" -> {
                    UserScreen(viewModel = viewModel)
                }
                else -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Section: ${uiState.activeSection}")
                    }
                }
            }
        }

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
fun Sidebar(
    activeFolderId: String?,
    folders: List<Folder>,
    onFolderSelected: (String?) -> Unit,
    onToggleFolderLock: (Folder) -> Unit,
    onDeleteFolder: (String) -> Unit,
    onCreateFolder: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(80.dp)
            .fillMaxHeight()
            .background(Color.White.copy(alpha = 0.5f))
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Folder", fontSize = 10.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        IconButton(onClick = { onFolderSelected(null) }) {
            Icon(Icons.Default.Home, contentDescription = "All", tint = if (activeFolderId == null) Color(0xFFFF5E62) else Color.Gray)
        }
        folders.forEach { folder ->
            val context = LocalContext.current
            var showMenu by remember { mutableStateOf(false) }
            
            Box {
                IconButton(
                    onClick = { 
                        if (folder.isLocked) {
                            com.k1031oct.nfa.util.BiometricHelper.showBiometricPrompt(
                                activity = context as androidx.fragment.app.FragmentActivity,
                                onSuccess = { onFolderSelected(folder.id) },
                                onError = { /* Handle error */ }
                            )
                        } else {
                            onFolderSelected(folder.id) 
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (folder.isLocked) Icons.Default.Lock else Icons.Default.Folder,
                        contentDescription = folder.name,
                        tint = if (activeFolderId == folder.id) Color(0xFFFF5E62) else Color.Gray,
                        modifier = android.view.View.OnLongClickListener { showMenu = true; true }.let { Modifier } // Placeholder for long click
                    )
                }
                
                // Overlay an invisible clickable for long press because IconButton doesn't support it easily
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onLongPress = { showMenu = true }
                            )
                        }
                )

                DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                    DropdownMenuItem(
                        text = { Text(if (folder.isLocked) "Unlock Folder" else "Lock Folder") },
                        onClick = { showMenu = false; onToggleFolderLock(folder) },
                        leadingIcon = { Icon(if (folder.isLocked) Icons.Default.LockOpen else Icons.Default.Lock, null) }
                    )
                    Divider()
                    DropdownMenuItem(
                        text = { Text("Delete Folder", color = Color.Red) },
                        onClick = { showMenu = false; onDeleteFolder(folder.id) },
                        leadingIcon = { Icon(Icons.Default.Delete, null, tint = Color.Red) }
                    )
                }
            }
        }
        IconButton(onClick = onCreateFolder) {
            Icon(Icons.Default.CreateNewFolder, contentDescription = "New", tint = Color.LightGray)
        }
    }
}

@Composable
fun RefillItem(label: String, isActive: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(
                color = if (isActive) Color(0xFFFF5E62) else Color.White,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label.take(2),
            color = if (isActive) Color.White else Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp
        )
    }
}

@Composable
fun NoteList(
    notes: List<Note>,
    activeRefill: String,
    viewModel: NoteViewModel,
    folders: List<Folder>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(notes) { note ->
            NoteCard(note, activeRefill, viewModel, folders)
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun NoteCard(
    note: Note,
    activeRefill: String,
    viewModel: NoteViewModel,
    folders: List<Folder>
) {
    var showMenu by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { viewModel.selectNote(note) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        if (note.imageUrl != null) {
            AsyncImage(
                model = note.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(Modifier.height(12.dp))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (activeRefill == "todo") {
                Checkbox(
                    checked = note.isCompleted,
                    onCheckedChange = { viewModel.toggleNoteCompletion(note) },
                    modifier = Modifier.padding(end = 8.dp)
                )
                LinearProgressIndicator(
                    progress = if (note.isCompleted) 1f else 0.3f, // Dummy logic for now
                    modifier = Modifier.weight(1f).height(4.dp).clip(RoundedCornerShape(2.dp)),
                    color = Color(0xFFFF5E62),
                    trackColor = Color(0xFFFF5E62).copy(alpha = 0.1f)
                )
                Spacer(Modifier.width(8.dp))
            }
            
            IconButton(onClick = { viewModel.toggleFavorite(note) }) {
                Icon(
                    imageVector = if (note.isFavorite) Icons.Default.Star else Icons.Default.StarBorder,
                    contentDescription = "Favorite",
                    tint = if (note.isFavorite) Color(0xFFFFD700) else Color.LightGray
                )
            }
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = note.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )
                if (note.tags.isNotEmpty()) {
                    Spacer(Modifier.height(4.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        items(note.tags) { tag ->
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = Color(0xFFFF5E62).copy(alpha = 0.1f)
                            ) {
                                Text(
                                    text = "#$tag",
                                    fontSize = 10.sp,
                                    color = Color(0xFFFF5E62),
                                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
                }
            }

            Box {
                IconButton(onClick = { showMenu = true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "More", tint = Color.LightGray)
                }
                DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                    DropdownMenuItem(
                        text = { Text("Edit") },
                        onClick = { showMenu = false; viewModel.selectNote(note) },
                        leadingIcon = { Icon(Icons.Default.Edit, null) }
                    )
                    val context = androidx.compose.ui.platform.LocalContext.current
                    DropdownMenuItem(
                        text = { Text("Share") },
                        onClick = { showMenu = false; com.k1031oct.nfa.util.ExportHelper.shareNoteAsText(context, note) },
                        leadingIcon = { Icon(Icons.Default.Share, null) }
                    )
                    folders.forEach { folder ->
                        DropdownMenuItem(
                            text = { Text("Move to ${folder.name}") },
                            onClick = { showMenu = false; viewModel.moveToFolder(note, folder.id) },
                            leadingIcon = { Icon(Icons.Default.Folder, null) }
                        )
                    }
                    if (note.folderId != null) {
                        DropdownMenuItem(
                            text = { Text("Remove from Folder") },
                            onClick = { showMenu = false; viewModel.moveToFolder(note, null) },
                            leadingIcon = { Icon(Icons.Default.Delete, null) }
                        )
                    }
                    Divider()
                    DropdownMenuItem(
                        text = { Text("Delete", color = Color.Red) },
                        onClick = { showMenu = false; viewModel.deleteNote(note.id, note.title) },
                        leadingIcon = { Icon(Icons.Default.Delete, null, tint = Color.Red) }
                    )
                }
            }
        }
        
        if (note.content.isNotBlank()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.content,
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 2,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
            )
        }
        }
    }
}
