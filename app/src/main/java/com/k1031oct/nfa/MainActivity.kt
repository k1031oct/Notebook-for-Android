package com.k1031oct.nfa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.fragment.app.FragmentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint

import androidx.hilt.navigation.compose.hiltViewModel
import android.util.Log
import androidx.compose.runtime.*
import com.k1031oct.nfa.ui.screens.MainScreen
import com.k1031oct.nfa.ui.screens.EditorScreen
import com.k1031oct.nfa.ui.screens.LoginScreen
import com.google.firebase.auth.FirebaseAuth
import com.k1031oct.nfa.ui.theme.NotebookTheme
import com.k1031oct.nfa.ui.viewmodels.NoteViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ORBIT", "Notebook MainActivity Created - Streaming Active")
        
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("152907979312-7j1qo8c3hu92dkknbi3kkhu7pg3et4gl.apps.googleusercontent.com") 
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)

        setContent {
            var isLoggedIn by remember { mutableStateOf(FirebaseAuth.getInstance().currentUser != null) }
            
                val viewModel: NoteViewModel = hiltViewModel()
                val uiState by viewModel.uiState.collectAsState()
                
                val darkTheme = when (uiState.themeMode) {
                    com.k1031oct.nfa.ui.states.ThemeMode.SYSTEM -> androidx.compose.foundation.isSystemInDarkTheme()
                    com.k1031oct.nfa.ui.states.ThemeMode.LIGHT -> false
                    com.k1031oct.nfa.ui.states.ThemeMode.DARK -> true
                }

                NotebookTheme(darkTheme = darkTheme) {

                val launcher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.StartActivityForResult()
                ) { result ->
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    try {
                        val account = task.getResult(ApiException::class.java)
                        account?.idToken?.let { idToken ->
                            viewModel.signInWithGoogle(idToken) { 
                                isLoggedIn = true 
                            }
                        }
                    } catch (e: Exception) {
                        Log.e("ORBIT", "Google Sign-In Activity Result failed", e)
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (!isLoggedIn) {
                        com.k1031oct.nfa.ui.screens.LoginScreen(onLoginSuccess = { 
                            isLoggedIn = true 
                            viewModel.loadNotes()
                        }, onGoogleLoginClick = {
                            launcher.launch(googleSignInClient.signInIntent)
                        })
                    } else if (uiState.isEditing && uiState.selectedNote != null) {
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


