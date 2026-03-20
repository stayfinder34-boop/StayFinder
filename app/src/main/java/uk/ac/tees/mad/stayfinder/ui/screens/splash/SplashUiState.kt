package uk.ac.tees.mad.stayfinder.ui.screens.splash

sealed class SplashUiState{
    object Loading : SplashUiState()
    object NavigateToHome : SplashUiState()
    object NavigateToAuth : SplashUiState()
}

