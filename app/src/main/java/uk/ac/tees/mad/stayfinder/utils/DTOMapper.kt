package uk.ac.tees.mad.stayfinder.utils

import uk.ac.tees.mad.stayfinder.data.local.HotelEntity
import uk.ac.tees.mad.stayfinder.data.model.Destination
import uk.ac.tees.mad.stayfinder.data.model.DestinationDto
import uk.ac.tees.mad.stayfinder.data.model.Hotel
import uk.ac.tees.mad.stayfinder.data.model.HotelDto

fun HotelDto.toHotelEntity(destId : String): HotelEntity {
    return HotelEntity(
        id = hotelId,
        destId = destId,
        name = property.name,
        rating =property.reviewScore,
        location = property.location,
        ratingText = property.reviewScoreWord,
        imageUrl = property.photoUrls?.firstOrNull(),
        price = property.priceBreakdown?.grossPrice?.value ?:0.0,
        currency = property.priceBreakdown?.grossPrice?.currency ?:"USD",
        latitude = property.latitude,
        longitude = property.longitude,
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

fun HotelEntity.toDomain(): Hotel {
    return Hotel(
        id = id,
        name = name,
        rating = rating,
        location = location,
        ratingText = ratingText,
        imageUrl = imageUrl,
        price = price,
        currency = currency,
        latitude = latitude,
        longitude = longitude
    )
}





/**
 * this is an extension function of hotel dto which maps dto to domain model
 * and it send only the important data from the dto to the domain model
 */
