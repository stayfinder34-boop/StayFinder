package uk.ac.tees.mad.stayfinder.utils

import uk.ac.tees.mad.stayfinder.data.model.HotelDomain
import uk.ac.tees.mad.stayfinder.data.model.HotelDto

fun HotelDto.toDomainHotel() : HotelDomain{
    return HotelDomain(
        id = hotelId,
        name = hotelName,
        rating = reviewScore,
        ratingText = reviewScoreWord,
        city = city,
        imageUrl = mainPhotoUrl,
        price = minTotalPrice,
        currency = currencyCode,
        latitude = latitude,
        longitude = longitude
    )
}


/**
 * this is an extension function of hotel dto which maps dto to domain model
 * and it send only the important data from the dto to the domain model
 */
