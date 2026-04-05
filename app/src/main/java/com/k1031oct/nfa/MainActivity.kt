package com.k1031oct.nfa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint

import androidx.hilt.navigation.compose.hiltViewModel
import android.util.Log
import com.k1031oct.nfa.ui.screens.MainScreen
import com.k1031oct.nfa.ui.screens.EditorScreen
import com.k1031oct.nfa.ui.viewmodels.NoteViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ORBIT", "Notebook MainActivity Created - Streaming Active")
        
        setContent {
            Log.d("ORBIT", "Entering NotebookTheme...")
            NotebookTheme {
                val viewModel: NoteViewModel = hiltViewModel()
                val uiState by viewModel.uiState.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (uiState.isEditing && uiState.selectedNote != null) {
                        EditorScreen(
                            note = uiState.selectedNote!!,
                            viewModel = viewModel,
                            onBack = { viewModel.selectNote(null) }
                        )
                    } else {
                        MainScreen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun NotebookTheme(content: @Composable () -> Unit) {
    MaterialTheme {
        content()
    }
}
