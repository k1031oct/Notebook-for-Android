package com.k1031oct.nfa.ui.screens

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
fun AppShortcutsScreen() {
    val context = LocalContext.current
    val packageManager = context.packageManager
    val apps = remember { getInstalledApps(packageManager) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("App Shortcuts", fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp))
        
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(apps) { app ->
                AppItem(app, onClick = {
                    val intent = packageManager.getLaunchIntentForPackage(app.packageName)
                    if (intent != null) {
                        context.startActivity(intent)
                    }
                })
            }
        }
    }
}

data class AppInfo(
    val name: String,
    val packageName: String,
    val icon: android.graphics.drawable.Drawable
)

fun getInstalledApps(pm: PackageManager): List<AppInfo> {
    val intent = Intent(Intent.ACTION_MAIN, null)
    intent.addCategory(Intent.CATEGORY_LAUNCHER)
    val list = pm.queryIntentActivities(intent, 0)
    return list.map {
        AppInfo(
            name = it.loadLabel(pm).toString(),
            packageName = it.activityInfo.packageName,
            icon = it.loadIcon(pm)
        )
    }.sortedBy { it.name }
}

@Composable
fun AppItem(app: AppInfo, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = androidx.compose.ui.graphics.Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberDrawablePainter(app.icon),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
            Spacer(Modifier.width(16.dp))
            Column {
                Text(app.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(app.packageName, fontSize = 10.sp, color = androidx.compose.ui.graphics.Color.Gray)
            }
        }
    }
}
