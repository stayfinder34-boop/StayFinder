package uk.ac.tees.mad.stayfinder.data.repository

import uk.ac.tees.mad.stayfinder.data.model.HotelDomain
import uk.ac.tees.mad.stayfinder.data.remote.HotelApiService
import uk.ac.tees.mad.stayfinder.utils.toDomainHotel

class HotelRepositoryImpl(private val apiService: HotelApiService): HotelRepository {

   override suspend fun getHotelList(
        latitude: Double,
        longitude: Double,
        arrivalDate: String,
        departureDate: String
    ): Result<List<HotelDomain>> {

        return try {
            val response = apiService.getHotelList(
                latitude,
                longitude,
                arrivalDate,
                departureDate
            )
            return if (response.status) {
                Result.success(response.data.result.map { it.toDomainHotel() })
            } else {
                Result.failure(Exception("API returned status false"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}


/**
 * this is Hotel repository has one function get hotel list which eventually sends
 * hotel list and mapped to domain to make it cleaner and api call is being done with
 * try and catch block to catch any exception and easily debug.
 */