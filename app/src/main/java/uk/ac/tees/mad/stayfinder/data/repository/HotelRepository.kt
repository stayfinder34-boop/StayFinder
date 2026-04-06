package uk.ac.tees.mad.stayfinder.data.repository

import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.stayfinder.data.local.HotelEntity
import uk.ac.tees.mad.stayfinder.data.model.Destination
import uk.ac.tees.mad.stayfinder.data.model.Hotel

interface HotelRepository {
    suspend fun searchHotelList(
       destinationId: String,
        arrivalDate: String,
        departureDate: String
    ) : Result<Unit>

    suspend fun searchDestinations(
        query: String
    ): Result<List<Destination>>

    suspend fun fetchHotelList(): Flow<List<Hotel>>

    fun getHotelById( id : Long) : HotelEntity?
}
