package uk.ac.tees.mad.stayfinder.ui.screens.home

import uk.ac.tees.mad.stayfinder.data.model.Destination
import uk.ac.tees.mad.stayfinder.data.model.Hotel

data class HomeUiState(
    val isLoading : Boolean = false,
    val error :String? = null,
    val query: String = "",
    val selectedDestination : Destination = Destination(),
    val destinationList : List<Destination> = emptyList(),
    val hotelList : List<Hotel> = emptyList()
)
