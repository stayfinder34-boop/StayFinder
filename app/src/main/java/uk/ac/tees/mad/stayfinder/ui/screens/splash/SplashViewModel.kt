package uk.ac.tees.mad.stayfinder.ui.screens.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import uk.ac.tees.mad.stayfinder.StayFinderApp

class SplashViewModel(application : Application)
    : AndroidViewModel(application) {
        private val firebaseAuth : FirebaseAuth =
            (application as StayFinderApp).container.firebaseAuth

        private val _splashUiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val splashUiState = _splashUiState.asStateFlow()


    init {
        resolveSplash()
    }

    private fun resolveSplash(){
        when{
            firebaseAuth.currentUser != null -> {
                _splashUiState.value = SplashUiState.NavigateToHome
            }
            else -> {
                _splashUiState.value = SplashUiState.NavigateToAuth
            }
        }
    }
}


/**
 * this is splash viewmodel class it will be called the moment activity is launched
 * and it will resolve whether the landing screen is going to be auth or home screen
 * depending upon the authenticated or not .
 * resolve splash ( ) is actual function that is called inside the init block
 * init block execute the moment instance of that class is created
 */