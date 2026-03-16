package uk.ac.tees.mad.stayfinder.utils

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain
            .request()
            .newBuilder()
            .addHeader("x-rapidapi-host", "booking-com15.p.rapidapi.com")
            .addHeader("x-rapidapi-key", RAPID_API_KEY)
            .build()
        return chain.proceed(request)
    }

}

const val RAPID_API_KEY = "5fb7171397msh21a68fd87fed071p1c8decjsn4f379fa15898"