package uk.ac.tees.mad.stayfinder.ui.screens.auth.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Outline
import uk.ac.tees.mad.stayfinder.ui.theme.Dimens
import uk.ac.tees.mad.stayfinder.ui.theme.StayFinderShapes

@Composable
fun EmailField(email : String ,
               onValueChange:(String)-> Unit){

    Column {
        Text(
            text = "email",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(
            modifier = Modifier.height(Dimens.SpacerSmall)
        )
        OutlinedTextField(
            value = email ,
            onValueChange = onValueChange,
            shape = StayFinderShapes.medium,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "abc@gmail.com"
                )
            },
            leadingIcon = {
                Icon(
                   imageVector = Icons.Default.Email,
                    contentDescription = "email",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        )
    }
}