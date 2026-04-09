package uk.ac.tees.mad.stayfinder.ui.screens.auth

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.stayfinder.PreferenceManager
import uk.ac.tees.mad.stayfinder.StayFinderApp
import uk.ac.tees.mad.stayfinder.data.repository.AuthRepository
import uk.ac.tees.mad.stayfinder.utils.AuthMode

class AuthViewModel (application: Application)
    : AndroidViewModel(application) {

        private val authRepository: AuthRepository =
            (application as StayFinderApp).container.authRepository

    private val preferenceManager : PreferenceManager =
        (application as StayFinderApp).container.preferency

        private val _authUiState = MutableStateFlow(AuthUiState())
    val authUiState = _authUiState.asStateFlow()



    fun onEmailChange(email : String){
        _authUiState.update {
            it.copy(
                email = email
            )
        }
    }

    fun onPasswordChange(password : String){
        _authUiState.update {
            it.copy(
                password = password
            )
        }
    }

    fun onConfirmPasswordChange(confirmPassword : String){
        _authUiState.update {
            it.copy(
                confirmPassword = confirmPassword
            )
        }
    }

    fun onAuthModeChange(authMode : AuthMode){
        _authUiState.update {
            it.copy(
                selectedMode = authMode
            )
        }
        reset()
    }

    fun onSubmitButtonClick(){
        when(_authUiState.value.selectedMode){
            AuthMode.LOGIN -> login()
            AuthMode.SIGNUP -> signup()
        }
        reset()
    }


    private fun login(){
        val state = _authUiState.value
       viewModelScope.launch {
           _authUiState.update {
               it.copy(
                   isLoading = true
               )
           }

           authRepository.loginUser(
               state.email ,
               state.password
           ).onSuccess {
               preferenceManager.setLoggedIn(true)
               _authUiState.update {
                   it.copy(
                       isLoading = false,
                       navigateToHome = true,
                       error = null
                   )
               }
           }.onFailure { error->
               Log.d("Auth" , "${error.message}")
               _authUiState.update {
                   it.copy(
                       isLoading = false ,
                       error = error.message
                   )
               }
           }
       }
    }

    private fun reset(){
        _authUiState.update {
            it.copy(
                email = "" ,
                password = "",
                confirmPassword = ""
            )
        }
    }

    private fun signup() {
        val state = _authUiState.value
        viewModelScope.launch {
            _authUiState.update {
                it.copy(
                    isLoading = true
                )
            }
            authRepository.registerUser(
                email = state.email,
                password = state.password
            )
                .onSuccess {
                    preferenceManager.setLoggedIn(true)
                    _authUiState.update {
                        it.copy(
                            isLoading = false,
                            navigateToHome = true
                        )
                    }
                }.onFailure { error ->
                    _authUiState.update {
                        it.copy(
                            isLoading = false,
                            error = error.message
                        )
                    }
                }
        }
    }
}