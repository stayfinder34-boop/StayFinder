package uk.ac.tees.mad.stayfinder.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uk.ac.tees.mad.stayfinder.data.local.HotelDao
import uk.ac.tees.mad.stayfinder.data.local.HotelEntity
import uk.ac.tees.mad.stayfinder.data.model.Destination
import uk.ac.tees.mad.stayfinder.data.model.Hotel
import uk.ac.tees.mad.stayfinder.data.remote.HotelApiService
import uk.ac.tees.mad.stayfinder.utils.toDomain
import uk.ac.tees.mad.stayfinder.utils.toHotelEntity

class HotelRepositoryImpl(private val apiService: HotelApiService ,
    private val dao: HotelDao)
    : HotelRepository {

   override suspend fun searchHotelList(
       destinationId: String,
       arrivalDate: String,
       departureDate: String
   ): Result<Unit> {

       return try{

           val response = apiService
               .searchHotelsByCity(destId = destinationId ,
                   arrivalDate = arrivalDate ,
                   departureDate = departureDate)

           /**
            * here we will be caching the hotel information into this simply and database is returning flow
            * so it will keep on sending the latest values to the viewmodel and with this it will be realtime
            **/

           if(response.status){
               dao.deleteAllHotels(destinationId)
               dao.insertHotels(
                   hotels = response.data.hotels.map { it.toHotelEntity(destinationId) }
               )
               Result.success(Unit)
           }
           else{
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

    override suspend fun fetchHotelList(): Flow<List<Hotel>> {
        return dao
            .getAllHotels()
            .map {entities ->
                entities.map { it.toDomain() }
            }
    }

    override fun getHotelById(id : Long): HotelEntity? {
        return dao.getHotelById(
            id = id
        )
    }


}


/**
 * this is Hotel repository has one function get hotel list which eventually sends
 * hotel list and mapped to domain to make it cleaner and api call is being done with
 * try and catch block to catch any exception and easily debug.
 * this repository will return list of hotels based on the city
 */

/**
 * fetch hotel list will return the list of hotel which is cached inside the room
 * and viewmodel will keep on collecting the updates from database
 */