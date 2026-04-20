package uk.ac.tees.mad.stayfinder.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.stayfinder.ui.theme.Dimens

/**
 * this is a top bar the is being shown at the top it will have the name of application
 * and will have the location icon and logout icon
 * location icon will provide the current location and logout icon will simply logout the
 * user .
 * I am having a custom top bar since i want full control over user interface so for that
 * reason i am using custom top bar otherwise could use the scaffold which hase inbuilt
 * top bar .
 **/


@Composable
fun HomeTopBar(
    currentLocation: String,
    isExpanded: Boolean,
    onLogoutClick: () -> Unit,
    onLocationClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(Dimens.TopBarHeight),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "StayFinder",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(start = Dimens.SpacerSmall)
                .weight(1f)
        )
        Box {
            IconButton(
                onClick = {
                    onLocationClick(!isExpanded)
                },
                colors = IconButtonDefaults
                    .iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "location",
                )
            }

            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { onLocationClick(false) },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = Dimens.SmallPadding),
                shape = RoundedCornerShape(8.dp)
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            text = currentLocation
                        )
                    },
                    onClick = {},
                    colors = MenuDefaults
                        .itemColors(
                            textColor = MaterialTheme.colorScheme.onBackground
                        ),
                )
            }
        }
        Spacer(modifier = Modifier.width(Dimens.SpacerSmall))
        IconButton(
            onClick = onLogoutClick ,
            colors = IconButtonDefaults
                .iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
        ) {
            Icon(
                imageVector = Icons.Default.Logout ,
                contentDescription = "logout"
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
    }
}