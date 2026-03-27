package uk.ac.tees.mad.stayfinder.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import uk.ac.tees.mad.stayfinder.data.model.DestinationResponse
import uk.ac.tees.mad.stayfinder.data.model.HotelSearchResponse

interface HotelApiService {

    @GET("api/v1/hotels/searchHotels")
    suspend fun searchHotelsByCity(
        @Query("dest_id") destId: String,
        @Query("search_type") searchType: String = "CITY",
        @Query("arrival_date") arrivalDate: String,
        @Query("departure_date") departureDate: String,
        @Query("adults") adults: Int = 1,
        @Query("room_qty") roomQty: Int = 1,
        @Query("page_number") pageNumber: Int = 1,
        @Query("units") units: String = "metric",
        @Query("temperature_unit") temperatureUnit: String = "c",
        @Query("languagecode") languageCode: String = "en-us",
        @Query("currency_code") currencyCode: String = "USD"
    ): HotelSearchResponse


    @GET("api/v1/hotels/searchDestination")
    suspend fun searchDestination(
        @Query("query") query: String
    ): DestinationResponse

}

/**
 * this is an api service which takes latitude , longitude ,which will be by default
 * sent through the location services will also give the flexibility to user and also will
 * have to feed arrival date and departure date will be 3 days after the arrival date and
 * it will be default sent automatically.
 *
 * search destination function is added to find out the destination id which will be
 * passed to find out the hotels list
 **/