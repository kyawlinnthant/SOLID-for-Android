package mdy.klt.dependencyinjectionforandroid.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mdy.klt.dependencyinjectionforandroid.auth.domain.usecase.Login
import mdy.klt.dependencyinjectionforandroid.core.common.Resource
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val login : Login
): ViewModel() {
    fun login() {
        viewModelScope.launch {
            val result = login(
                username = "",
                password = ""
            )
            when (result) {
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {

                }
            }
        }
    }
}