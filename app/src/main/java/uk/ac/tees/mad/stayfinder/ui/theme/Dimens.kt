package uk.ac.tees.mad.stayfinder.ui.theme

import androidx.compose.ui.unit.dp

object Dimens {

    /**
     * Padding & Spacing
     */

    val ExtraSmallPadding = 4.dp
    val SmallPadding = 8.dp
    val MediumPadding = 16.dp
    val LargePadding = 24.dp
    val ExtraLargePadding = 32.dp
    val ScreenPadding = 20.dp

    /**
     * Button
     */

    val ButtonHeight = 52.dp
    val ButtonCornerRadius = 14.dp

    /**
     * TextField
     */

    val TextFieldCornerRadius = 12.dp
    val TextFieldHeight = 56.dp

    /**
     * Cards
     */

    val CardCornerRadius = 16.dp
    val CardElevation = 6.dp
    val CardInternalPadding = 12.dp

    /**
     * App Bar
     */

    val TopBarHeight = 64.dp
    val TopBarIconSize = 24.dp

    /**
     * Icons
     */

    val SmallIcon = 18.dp
    val MediumIcon = 24.dp
    val LargeIcon = 32.dp


    /**
     *  Hotel Image
     */


    val HotelImageHeight = 180.dp
    val ThumbnailSize = 72.dp


    /**
     *  Spacers
    **/

    val SpacerSmall = 8.dp
    val SpacerMedium = 16.dp
    val SpacerLarge = 24.dp

}

/**
 * this is basically a centralised file to have the different size
 * like horizontal padding , vertical , small , large , extra large ,
 * button size , topbar size
 * this should be followed to avoid hardcoding of values since hardcoding  can led
 * to inconsistent ui and led to bad ux
 */