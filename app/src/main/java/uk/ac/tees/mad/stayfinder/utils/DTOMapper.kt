package uk.ac.tees.mad.stayfinder.utils

import uk.ac.tees.mad.stayfinder.data.model.Destination
import uk.ac.tees.mad.stayfinder.data.model.DestinationDto
import uk.ac.tees.mad.stayfinder.data.model.Hotel
import uk.ac.tees.mad.stayfinder.data.model.HotelDto

fun HotelDto.toDomain(): Hotel {
    return Hotel(
        id = hotelId,
        name = property.name,
        rating = property.reviewScore,
        ratingText = property.reviewScoreWord,
        imageUrl = property.photoUrls?.firstOrNull(),
        price = property.priceBreakdown?.grossPrice?.value,
        currency = property.priceBreakdown?.grossPrice?.currency,
        latitude = property.latitude,
        longitude = property.longitude
    )
}

fun DestinationDto.toDomain(): Destination {
    return Destination(
        id = destId,
        searchType = searchType,
        name = name,
        label = label,
        type = destType
    )
}





/**
 * this is an extension function of hotel dto which maps dto to domain model
 * and it send only the important data from the dto to the domain model
 */
