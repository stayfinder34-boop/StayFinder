package uk.ac.tees.mad.stayfinder.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.stayfinder.data.model.Destination
import uk.ac.tees.mad.stayfinder.ui.screens.home.components.HomeTopBar
import uk.ac.tees.mad.stayfinder.ui.screens.home.components.HotelCard
import uk.ac.tees.mad.stayfinder.ui.screens.home.components.SearchBar
import uk.ac.tees.mad.stayfinder.ui.theme.Dimens

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
){
    val uiState by viewModel.homeUiState.collectAsStateWithLifecycle()

    HomeScreenContent(uiState = uiState ,
        onQueryChange = viewModel::onQueryChange ,
        onSelectDestination = viewModel::onSelectDestination ,
        onSearch = viewModel::onSearch)
}


@Composable
fun HomeScreenContent(uiState: HomeUiState ,
                      onQueryChange: (String) -> Unit,
                      onSelectDestination:(Destination) -> Unit ,
                      onSearch:()-> Unit){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
    ){
        HomeTopBar(
            onLogoutClick =  {} ,
            onLocationClick = {} ,
        )
        Spacer(
            modifier = Modifier.height(Dimens
                .ExtraSmallPadding)
        )
        SearchBar(
            query = uiState.query,
            onQueryChange = onQueryChange,
            onSearch = onSearch,
            onDestClick = onSelectDestination,
            destinations = uiState.destinationList,
        )
        Spacer(
            modifier = Modifier.height(Dimens
                .ExtraSmallPadding)
        )

        LazyColumn (
            modifier = Modifier
                .padding(horizontal = Dimens.ScreenPadding)
                .fillMaxWidth() ,
            contentPadding = PaddingValues(Dimens.ExtraSmallPadding) ,
            verticalArrangement = Arrangement.spacedBy(Dimens.SpacerSmall)
        ){
            items(uiState.hotelList){ hotel->
                HotelCard(
                    hotel = hotel
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun HomeScreenPreview(){
    HomeScreenContent(uiState = HomeUiState() ,
        onQueryChange = {} ,
        onSelectDestination = {} ,
        onSearch = {})
}


/**
 * this is a parent user interface whose name is home screen
 * it will show the list of destination from data base which is being cached when user
 * search the new hotel it will simply store to database and then interface will show
 * from database
 * for preview screen is divided into two part
 * 1. home screen
 * 2. home screen content
 * and required viewmodel function have been passed from top to bottom in home screen to
 * content screen to have better readability and it follows the principle even goes up and
 * state go down
 */
