package uk.ac.tees.mad.stayfinder.ui.screens.detail

import uk.ac.tees.mad.stayfinder.data.model.Hotel

data class DetailUiState (
    val isLoading: Boolean = false ,
    val error: String? = null ,
    val hotel : Hotel ? = null
)