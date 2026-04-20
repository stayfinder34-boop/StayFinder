package uk.ac.tees.mad.stayfinder.ui.screens.home

import uk.ac.tees.mad.stayfinder.data.model.Destination
import uk.ac.tees.mad.stayfinder.data.model.Hotel

data class HomeUiState(
    val isLoading : Boolean = false,
    val error :String? = null,
    val query: String = "",
    val currentLocation :String = "" ,
    val isSearchBarActive : Boolean = false ,
    val navigateToAuth : Boolean = false,
    val shouldRequestLocation: Boolean = false ,
    val shouldRequestNotification : Boolean = false ,
    val isLocationCardExtended : Boolean = false ,
    val isSearchLoading : Boolean = false ,
    val selectedDestination : Destination = Destination(),
    val destinationList : List<Destination> = emptyList(),
    val hotelList : List<Hotel> = emptyList()
)
