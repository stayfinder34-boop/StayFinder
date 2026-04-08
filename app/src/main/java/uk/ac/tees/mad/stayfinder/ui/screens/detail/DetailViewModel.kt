package uk.ac.tees.mad.stayfinder.ui.screens.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.compose
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.stayfinder.StayFinderApp
import uk.ac.tees.mad.stayfinder.data.repository.HotelRepository

class DetailViewModel(application: Application)
    : AndroidViewModel(application){

        private val repository : HotelRepository =
            (application as StayFinderApp).container.hotelRepository

        private val _detailUiState = MutableStateFlow(DetailUiState())
        val detailUiState = _detailUiState.asStateFlow()


    fun fetchById(id : Long){
        _detailUiState.update {
            it.copy(
                isLoading = true
            )
        }

        viewModelScope.launch {
            repository
                .getHotelById(id)
                .collect{ hotel->
                    _detailUiState.update {
                        it.copy(
                            isLoading = false ,
                            hotel = hotel ,
                            error = null
                        )
                    }
                }
        }
    }
}