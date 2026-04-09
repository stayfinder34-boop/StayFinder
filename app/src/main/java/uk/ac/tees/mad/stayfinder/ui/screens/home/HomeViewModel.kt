package uk.ac.tees.mad.stayfinder.ui.screens.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.ac.tees.mad.stayfinder.PreferenceManager
import uk.ac.tees.mad.stayfinder.StayFinderApp
import uk.ac.tees.mad.stayfinder.data.model.Destination
import uk.ac.tees.mad.stayfinder.data.repository.AuthRepository
import uk.ac.tees.mad.stayfinder.data.repository.HotelRepository

class HomeViewModel (application: Application)
    : AndroidViewModel(application){

        private val hotelRepository : HotelRepository =
            (application as StayFinderApp).container.hotelRepository

    private val preferenceManager : PreferenceManager =
        (application as StayFinderApp).container.preferency

    private val authRepository : AuthRepository =
        (application as StayFinderApp).container.authRepository

        private  val _homeUiState = MutableStateFlow(HomeUiState())
    val homeUiState = _homeUiState.asStateFlow()

    private val queryFlow = MutableStateFlow("")


    /**
     * init {} block runs when object of viewmodel is created ,
     * it is used as engine starter in viewmodel
     * since query flow is a flow it will keep on listening the changes in query and will keep on searching when criteria is met
     * init is working as engine starter
     */

    init {
        Log.d("HomeViewModel" , "init for room")
            fetchFromRoom()
        observeQueryChange()
    }

    fun onLogoutClick(){
        authRepository
            .logout()
            .onFailure {
                _homeUiState.update {
                    it.copy(
                        error = it.error
                    )
                }
            }
            .onSuccess {
                preferenceManager.setLoggedIn(false)
                _homeUiState.update {
                    it.copy(
                        navigateToAuth = true
                    )
                }
            }
    }

    /**
     * onQueryChange will be responsible for keeping the changing text in the search bar
     * whenever user will type something in the search bar it will be updated
     **/
    fun onQueryChange(query : String){
        _homeUiState.update {
            it.copy(
                query = query
            )
        }
        queryFlow.value = query
    }

    /**
     * onSelectDestination will be responsible for selecting the destination available from
     * server and updating the selected destination as well as query and after that user cal click search
     **/
    fun onSelectDestination(destination: Destination){
        _homeUiState.update {
            it.copy(
                query = destination.name ,
                selectedDestination = destination
            )
        }
    }

    /**
     * onSearch is function responsible for searching the hotel list and it will fetch the list of hotel
     * from server and update the hotel list into the database then we will read from data base
     */

    fun onSearch(){
        Log.d("HomeViewModel" ,"search called")
        viewModelScope.launch {
            hotelRepository.searchHotelList(
                destinationId = _homeUiState.value.selectedDestination.id,
                arrivalDate  = "2026-02-26",
                departureDate = "2026-02-28"
            )
                .onFailure {error->
                    Log.d("HomeViewModel" , "${error.message}")
                }
                .onSuccess {
                    Log.d("HomeViewModel" , "success")
                }
        }
    }

    /**
     * observeQueryChange() this is a private function will keep on observing the query the moment it meets criteria
     * 1. query is distinct
     * 2. debounce time completed then it will automatically calls the getDestinations from repository
     **/

    @OptIn(FlowPreview::class)
    private fun observeQueryChange(){
        Log.d("HomeViewModel" , "observing")
        viewModelScope.launch {
            queryFlow
                .debounce (500)
                .filter { it.length >=3 }
                .distinctUntilChanged()
                .collectLatest { query->
                    Log.d("HomeViewModel" , query)
                    fetchDestination(query)
                }
        }
    }

   private suspend fun fetchDestination(query: String){
       hotelRepository
           .searchDestinations(query)
           .fold(
               onSuccess = {destinations ->
                   Log.d("HomeViewModel" , "$destinations")
                   _homeUiState.update {
                       it.copy(
                           destinationList = destinations
                       )
                   }
               } ,
               onFailure = { error->
                   _homeUiState.update {
                       it.copy(
                           error  = error.message
                       )
                   }
               }
           )
    }

    private fun fetchFromRoom() {
        _homeUiState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            hotelRepository
                .fetchHotelList()
                .collect { list ->
                    _homeUiState.update {
                        it.copy(
                            isLoading = false,
                            hotelList = list
                        )
                    }
                }
        }
    }
}