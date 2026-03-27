package uk.ac.tees.mad.stayfinder.data.repository

import uk.ac.tees.mad.stayfinder.data.model.Destination
import uk.ac.tees.mad.stayfinder.data.model.Hotel

interface HotelRepository {
    suspend fun getHotelList(
       destinationId: String,
        arrivalDate: String,
        departureDate: String
    ) : Result<List<Hotel>>

    suspend fun searchDestinations(
        query: String
    ): Result<List<Destination>>
}
