package uk.ac.tees.mad.stayfinder.ui.screens.auth.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import uk.ac.tees.mad.stayfinder.ui.theme.Dimens
import uk.ac.tees.mad.stayfinder.utils.AuthMode

@Composable
fun AuthToggle(
    onSelectMode :(AuthMode)-> Unit ,
    selectedMode : AuthMode ,
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.ScreenPadding)
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.large
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        ModeItem(
            isSelected = selectedMode == AuthMode.LOGIN,
            text = AuthMode.LOGIN.name,
            onSelect = {
                onSelectMode(AuthMode.LOGIN)
            },
            modifier = Modifier.weight(1f)
        )

        ModeItem(
            isSelected = selectedMode == AuthMode.SIGNUP ,
            text = AuthMode.SIGNUP.name,
            onSelect = {
                onSelectMode(AuthMode.SIGNUP)
            },
            modifier = Modifier.weight(1f)
        )
    }
}


@Composable
fun ModeItem(isSelected : Boolean ,
             onSelect:()-> Unit ,
             text : String ,
             modifier :Modifier = Modifier
             ){

    TextButton(
        onClick = onSelect ,
        modifier = modifier ,
        shape = MaterialTheme.shapes.large,
        colors = ButtonDefaults.textButtonColors(
            containerColor = if (isSelected)
                MaterialTheme.colorScheme.primary
            else
                Color.Transparent,
            contentColor = if (isSelected)
                MaterialTheme.colorScheme.onPrimary
            else
                MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}