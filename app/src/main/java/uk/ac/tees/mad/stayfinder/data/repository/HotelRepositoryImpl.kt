package uk.ac.tees.mad.stayfinder.data.repository

import uk.ac.tees.mad.stayfinder.data.model.Destination
import uk.ac.tees.mad.stayfinder.data.model.Hotel
import uk.ac.tees.mad.stayfinder.data.remote.HotelApiService
import uk.ac.tees.mad.stayfinder.utils.toDomain

class HotelRepositoryImpl(private val apiService: HotelApiService): HotelRepository {

   override suspend fun getHotelList(
        destinationId: String,
        arrivalDate: String,
        departureDate: String
    ): Result<List<Hotel>> {

       return try{
           val response = apiService
               .searchHotelsByCity(destId = destinationId ,
                   arrivalDate = arrivalDate ,
                   departureDate = departureDate)

           if(response.status){
             return   Result.success(response.data.hotels.map { it.toDomain() })
           }else{
               Result.failure(Exception("Something went wrong"))
           }

       }catch (e : Exception){
           Result.failure(e)
       }
    }


    override suspend fun searchDestinations(
        query: String
    ): Result<List<Destination>> {

        return try {
            val response = apiService.searchDestination(query)

            if (!response.status) {
                return Result.failure(Exception("Destination search failed"))
            }
            val cityDestinations = response.data
                .filter { it.destType == "city" }
                .map { it.toDomain() }

            Result.success(cityDestinations)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}


/**
 * this is Hotel repository has one function get hotel list which eventually sends
 * hotel list and mapped to domain to make it cleaner and api call is being done with
 * try and catch block to catch any exception and easily debug.
 * this repository will return list of hotels based on the city
 */