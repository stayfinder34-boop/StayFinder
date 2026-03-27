package uk.ac.tees.mad.stayfinder.ui.screens.auth

import uk.ac.tees.mad.stayfinder.utils.AuthMode
import uk.ac.tees.mad.stayfinder.utils.isEmailValid

data class AuthUiState(
    val isLoading :Boolean = false ,
    val email : String  = "",
    val password : String = "" ,
    val confirmPassword :String = "",
    val navigateToHome : Boolean = false,
    val error: String ? = null,
    val selectedMode : AuthMode = AuthMode.LOGIN
){
    val canSubmit : Boolean
        get() = when(selectedMode){
            AuthMode.LOGIN -> email.isEmailValid() && password.length >= 8
            AuthMode.SIGNUP -> email.isEmailValid() && password.length >= 8 && confirmPassword == password
        }
}

