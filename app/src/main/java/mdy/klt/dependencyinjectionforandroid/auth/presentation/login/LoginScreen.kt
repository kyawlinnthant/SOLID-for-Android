package mdy.klt.dependencyinjectionforandroid.auth.presentation.login

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen() {
    val vm: LoginViewModel = hiltViewModel()
}