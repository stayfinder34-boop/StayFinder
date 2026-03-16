package uk.ac.tees.mad.stayfinder.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import uk.ac.tees.mad.stayfinder.data.model.HotelSearchResponse

interface HotelApiService {

    @GET("api/v1/hotels/searchHotelsByCoordinates")
    suspend fun getHotelList(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("arrival_date") arrivalDate: String ,
        @Query("departure_date") departureDate: String
    ) : HotelSearchResponse
}

/**
 * this is an api service which takes latitude , longitude ,which will be by default
 * sent through the location services will also give the flexibility to user and also will
 * have to feed arrival date and departure date will be 3 days after the arrival date and
 * it will be default sent automatically.
 **/