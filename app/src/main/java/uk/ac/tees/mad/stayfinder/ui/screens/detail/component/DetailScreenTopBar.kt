package uk.ac.tees.mad.stayfinder.ui.screens.detail.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import uk.ac.tees.mad.stayfinder.ui.theme.Dimens

@Composable
fun DetailScreenTopBar(
    onBackClick:()-> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.SmallPadding)
            .height(Dimens.TopBarHeight) ,
        verticalAlignment = Alignment.CenterVertically
    ){
        IconButton(onClick = onBackClick){
            Icon(
                imageVector = Icons.Default.ArrowBack ,
                contentDescription = "back button"
            )
        }

        Text(
            text = "Detail" ,
            modifier = Modifier.padding(start = Dimens.ExtraSmallPadding) ,
            style = MaterialTheme.typography.titleLarge
        )
    }
}