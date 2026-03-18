package uk.ac.tees.mad.stayfinder.utils

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import uk.ac.tees.mad.stayfinder.data.remote.HotelApiService

object RetrofitClient{
    private  val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(ApiInterceptor())
        .build()

    val apiService: HotelApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://booking-com15.p.rapidapi.com/")
            .client(okHttpClient)
            .addConverterFactory(
                Json {
                    ignoreUnknownKeys = true
                }.asConverterFactory("application/json".toMediaType())
            )
            .build()
            .create(HotelApiService::class.java)
    }

}