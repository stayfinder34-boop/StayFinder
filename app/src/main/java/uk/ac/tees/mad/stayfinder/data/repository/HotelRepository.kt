package uk.ac.tees.mad.stayfinder.data.repository

import uk.ac.tees.mad.stayfinder.data.model.HotelDomain

interface HotelRepository {
    suspend fun getHotelList(
        latitude: Double,
        longitude: Double,
        arrivalDate: String,
        departureDate: String
    ) : Result<List<HotelDomain>>
}
