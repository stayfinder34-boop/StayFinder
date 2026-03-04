package uk.ac.tees.mad.stayfinder.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val StayFinderShapes = Shapes(
    small = RoundedCornerShape(Dimens.TextFieldCornerRadius),
    medium = RoundedCornerShape(Dimens.CardCornerRadius),
    large = RoundedCornerShape(24.dp)
)