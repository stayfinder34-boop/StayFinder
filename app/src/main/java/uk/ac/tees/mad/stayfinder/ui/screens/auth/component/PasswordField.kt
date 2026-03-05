package uk.ac.tees.mad.stayfinder.ui.screens.auth.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uk.ac.tees.mad.stayfinder.ui.theme.Dimens

@Composable
fun PasswordField(
    password : String ,
    onValueChange:(String)-> Unit ,
    isPasswordVisible : Boolean = false
){

    Column {
        Text(
            text = "password" ,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(
            modifier = Modifier.height(
                Dimens.SpacerSmall
            )
        )
        OutlinedTextField(
            value = password ,
            onValueChange = onValueChange ,
            shape = MaterialTheme.shapes.medium ,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "11111111"
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Password ,
                    contentDescription = "password" ,
                    tint = MaterialTheme.colorScheme.primary
                )
            },
        )
    }
}