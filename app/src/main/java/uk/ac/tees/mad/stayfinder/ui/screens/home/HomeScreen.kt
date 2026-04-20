package uk.ac.tees.mad.stayfinder.ui.screens.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.ac.tees.mad.stayfinder.data.model.Destination
import uk.ac.tees.mad.stayfinder.ui.screens.home.components.HomeTopBar
import uk.ac.tees.mad.stayfinder.ui.screens.home.components.HotelCard
import uk.ac.tees.mad.stayfinder.ui.screens.home.components.SearchBar
import uk.ac.tees.mad.stayfinder.ui.theme.Dimens

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel() ,
    onLogout:()-> Unit ,
    onDetailClick :(Long) -> Unit
){
    val uiState by viewModel.homeUiState.collectAsStateWithLifecycle()


    LaunchedEffect(uiState.navigateToAuth) {
        if(uiState.navigateToAuth){
            onLogout()
        }
    }

    val context = LocalContext.current

    val locationLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        viewModel.onLocationPermissionResult(granted)
    }

    val notificationLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted->
        if(granted){
            viewModel.scheduleReminder()
        }
    }

    LaunchedEffect(uiState.shouldRequestLocation) {

        if (uiState.shouldRequestLocation) {

            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                viewModel.onLocationPermissionResult(true)
            } else {
                locationLauncher.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            }
        }
    }


    LaunchedEffect(uiState.shouldRequestNotification) {

        if (uiState.shouldRequestNotification) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                if (ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    viewModel.scheduleReminder()
                } else {
                    notificationLauncher.launch(
                        Manifest.permission.POST_NOTIFICATIONS
                    )
                }
            } else {
                viewModel.scheduleReminder()
            }
            viewModel.resetNotificationFlag()
        }
    }


    HomeScreenContent(
        uiState = uiState ,
        onQueryChange = viewModel::onQueryChange ,
        onSelectDestination = viewModel::onSelectDestination ,
        onSearch = viewModel::onSearch ,
        onLogout = viewModel::onLogoutClick,
        onActiveChange = viewModel::onActiveChange ,
        onDetailClick = onDetailClick ,
        onLocationClick = {expanded ->
            viewModel.onLocationCLick(expanded)
            if (expanded) {
                if (ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    viewModel.onLocationPermissionResult(
                        granted = true,
                        isLocationClick = true
                    )
                } else {
                    locationLauncher.launch(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                }
            }
        }
    )

}


@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    onQueryChange: (String) -> Unit,
    onSelectDestination: (Destination) -> Unit,
    onSearch: () -> Unit,
    onLogout: () -> Unit,
    onDetailClick: (Long) -> Unit,
    onActiveChange: (Boolean) -> Unit ,
    onLocationClick: (Boolean) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .statusBarsPadding()
        ) {
            HomeTopBar(
                onLogoutClick = onLogout,
                onLocationClick = onLocationClick,
                currentLocation = uiState.currentLocation,
                isExpanded = uiState.isLocationCardExtended,
            )
            Spacer(
                modifier = Modifier.height(
                    Dimens
                        .ExtraSmallPadding
                )
            )
            SearchBar(
                query = uiState.query,
                isActive = uiState.isSearchBarActive ,
                onActiveChange = onActiveChange,
                onQueryChange = onQueryChange,
                onSearch = onSearch,
                onDestClick = onSelectDestination,
                destinations = uiState.destinationList,
                isLoading = uiState.isSearchLoading
            )
            Spacer(
                modifier = Modifier.height(
                    Dimens
                        .ExtraSmallPadding
                )
            )


            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = Dimens.ScreenPadding)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(Dimens.ExtraSmallPadding),
                verticalArrangement = Arrangement.spacedBy(Dimens.SpacerSmall)
            ) {

                if (uiState.hotelList.isEmpty()) {
                    item {
                        Text(
                            text = "No hotels found",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                items(uiState.hotelList) { hotel ->
                    HotelCard(
                        hotel = hotel,
                        onDetailClick = onDetailClick
                    )
                }
            }
        }

        if(uiState.isLoading){
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.5f)),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview(){
    HomeScreenContent(
        uiState = HomeUiState(),
        onQueryChange = {},
        onSelectDestination = {},
        onSearch = {},
        onLogout = {},
        onDetailClick = {},
        onActiveChange = {} ,
        onLocationClick = {}
    )
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
