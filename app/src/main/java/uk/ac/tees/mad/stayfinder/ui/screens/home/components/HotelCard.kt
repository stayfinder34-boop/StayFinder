package uk.ac.tees.mad.stayfinder.ui.screens.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import uk.ac.tees.mad.stayfinder.data.model.Hotel
import uk.ac.tees.mad.stayfinder.ui.theme.Dimens

@Composable
fun HotelCard(modifier: Modifier = Modifier,
              hotel: Hotel){

    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()){

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                AsyncImage(
                    model = hotel.imageUrl ,
                    contentDescription = "hotel image" ,
                    contentScale = ContentScale.Fit
                )


                Surface(
                    shape = RoundedCornerShape(50),
                    color = MaterialTheme.colorScheme.surface,
                    shadowElevation = 4.dp,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFA000),
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text  = hotel.rating.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Text(
                text = hotel.name ,
                modifier = Modifier.padding(Dimens.SmallPadding) ,
                fontWeight = FontWeight.SemiBold,
                maxLines = 3 ,
                overflow = TextOverflow.Ellipsis
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.SmallPadding) ,
                verticalAlignment = Alignment.CenterVertically){

                Icon(
                    imageVector = Icons.Default.LocationOn ,
                    contentDescription = "location"
                )
                Spacer(modifier = Modifier.width(Dimens.SmallPadding))
                Text(
                    text = hotel.location ,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
