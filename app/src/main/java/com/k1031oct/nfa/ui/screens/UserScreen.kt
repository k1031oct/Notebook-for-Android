package com.k1031oct.nfa.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import com.k1031oct.nfa.ui.viewmodels.NoteViewModel
import com.k1031oct.nfa.ui.states.ThemeMode
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Sync

@Composable
fun UserScreen(viewModel: NoteViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val user = uiState.currentUser

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "User Profile",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Profile Image
        if (user?.photoUrl != null) {
            AsyncImage(
                model = user.photoUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )
        } else {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier.size(100.dp),
                tint = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            // Profile Info Card
            SettingsCard(title = "Account Info", icon = Icons.Default.AccountCircle) {
                InfoRow(label = "Name", value = user?.displayName ?: "Guest")
                Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color.Gray.copy(alpha = 0.1f))
                InfoRow(label = "Email", value = user?.email ?: "Not logged in")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Appearance Card
            @OptIn(ExperimentalMaterial3Api::class)
            SettingsCard(title = "Appearance", icon = Icons.Default.Palette) {
                Text("Theme", fontSize = 14.sp, color = Color.Gray)
                Spacer(Modifier.height(8.dp))
                SingleChoiceSegmentedButtonRow(modifier = Modifier.fillMaxWidth()) {
                    ThemeMode.values().forEachIndexed { index, mode ->
                        SegmentedButton(
                            shape = SegmentedButtonDefaults.itemShape(index = index, count = 3),
                            onClick = { viewModel.setThemeMode(mode) },
                            selected = uiState.themeMode == mode
                        ) {
                            Text(mode.name.lowercase().capitalize())
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Calendar Settings Card
            SettingsCard(title = "Calendar", icon = Icons.Default.Translate) {
                Text("Holiday Country", fontSize = 14.sp, color = Color.Gray)
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    listOf("JP", "US").forEach { country ->
                        FilterChip(
                            selected = uiState.holidayCountry == country,
                            onClick = { viewModel.setHolidayCountry(country) },
                            label = { Text(country) },
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Integration Card
            SettingsCard(title = "Integrations", icon = Icons.Default.Sync) {
                IntegrationRow(
                    label = "Google Calendar Sync",
                    enabled = uiState.isGoogleCalendarConnected,
                    onToggle = { viewModel.toggleGoogleCalendar(it) }
                )
                Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color.Gray.copy(alpha = 0.1f))
                IntegrationRow(
                    label = "Google Docs Sync",
                    enabled = uiState.isGoogleDocsConnected,
                    onToggle = { viewModel.toggleGoogleDocs(it) }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Sign Out Button
            if (user != null) {
                Button(
                    onClick = { viewModel.signOut() },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5E62).copy(alpha = 0.1f), contentColor = Color(0xFFFF5E62)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Icon(Icons.Default.ExitToApp, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Sign Out", fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun SettingsCard(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, content: @Composable ColumnScope.() -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, null, tint = Color(0xFFFF5E62), modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Spacer(Modifier.height(16.dp))
            content()
        }
    }
}

@Composable
fun IntegrationRow(label: String, enabled: Boolean, onToggle: (Boolean) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier.weight(1f)) {
            Text(label, fontSize = 15.sp, fontWeight = FontWeight.Medium)
            Text(if (enabled) "Connected" else "Disconnected", fontSize = 12.sp, color = if (enabled) Color(0xFF4CAF50) else Color.Gray)
        }
        Switch(
            checked = enabled,
            onCheckedChange = onToggle,
            colors = SwitchDefaults.colors(checkedThumbColor = Color.White, checkedTrackColor = Color(0xFFFF5E62))
        )
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Column {
        Text(text = label, fontSize = 14.sp, color = Color.Gray)
        Text(text = value, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
    }
}
