package uk.ac.tees.mad.stayfinder.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import uk.ac.tees.mad.stayfinder.data.model.Hotel
import uk.ac.tees.mad.stayfinder.ui.screens.detail.component.DetailScreenTopBar
import uk.ac.tees.mad.stayfinder.ui.theme.Dimens

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = viewModel() ,
    onBack:()-> Unit ,
    id : Long
){

    LaunchedEffect(id) {
        viewModel.fetchById(id)
    }

    val uiState by viewModel.detailUiState.collectAsStateWithLifecycle()

    DetailScreenContent(
        hotel = uiState.hotel ,
        onBack = onBack
    )

}


@Composable
fun DetailScreenContent( hotel : Hotel? ,
                         onBack: () -> Unit){

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {

        DetailScreenTopBar(
            onBackClick = onBack
        )

        Spacer(
            modifier = Modifier.height(Dimens.SpacerSmall)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = Dimens.ScreenPadding)
        ) {
            LazyRow {
                items(hotel?.imageUrl?:emptyList()){ url->
                    LazyItem(url = url)
                }
            }

            Spacer(
                modifier = Modifier.height(Dimens.SpacerSmall)
            )

            Card(
                shape = RoundedCornerShape(Dimens.CardCornerRadius)  ,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
            ){
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp , vertical = 4.dp),
                ){
                    Icon(
                        imageVector = Icons.Default.StarOutline ,
                        contentDescription = "star" ,
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Text(
                        text = hotel?.rating.toString() ,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(Dimens.SpacerSmall)
            )

            hotel?.name?.let {
                Text(
                    text = it ,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(
                modifier = Modifier.height(Dimens.SpacerSmall)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth() ,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = Icons.Default.LocationOn ,
                    contentDescription = "location icon"
                )

                Spacer(
                    modifier = Modifier.width(Dimens.SpacerSmall)
                )

                Text(
                    text = hotel?.location ?: "mumbai"
                )
            }

            Spacer(
                modifier = Modifier.height(Dimens.SpacerSmall)
            )

            Text(
                text = "Review" ,
                style = MaterialTheme.typography.titleMedium,
            )

            Spacer(
                modifier = Modifier.height(Dimens.SpacerSmall)
            )

            Text(
                text = hotel?.ratingText ?: "No review available" ,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(
                modifier = Modifier.height(Dimens.SpacerSmall)
            )

            Row(modifier = Modifier.fillMaxWidth()){

                Column (
                    modifier = Modifier.weight(1f)
                ){
                    Text(
                        text = "Price"
                    )
                    Divider()

                    Text(
                        text = "Currency"
                    )
                    Divider()

                    Text(
                        text = "Latitude"
                    )
                    Divider()
                    Text(
                        text ="Longitude"
                    )
                    Divider()
                }


                Column( modifier = Modifier.weight(1f)) {

                    Text(
                        text = hotel?.price.toString()
                    )
                   Divider()
                    Text(
                        text = hotel?.currency.toString()
                    )
                    Divider()
                    Text(
                        text = hotel?.latitude.toString()
                    )
                    Divider()
                    Text(
                        text = hotel?.longitude.toString()
                    )
                    Divider()
                }
            }
        }
    }
}



@Composable
fun LazyItem(url: String) {
    Card(
        modifier = Modifier
            .width(260.dp)
            .height(180.dp)
            .padding(end = 12.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        AsyncImage(
            model = url,
            contentDescription = "hotel image",
            contentScale = ContentScale.Crop,  // IMPORTANT
            modifier = Modifier.fillMaxSize()
        )
    }
}

