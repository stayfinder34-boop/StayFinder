package uk.ac.tees.mad.stayfinder.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import uk.ac.tees.mad.stayfinder.data.remote.HotelApiService
import uk.ac.tees.mad.stayfinder.data.repository.AuthRepository
import uk.ac.tees.mad.stayfinder.data.repository.AuthRepositoryImpl
import uk.ac.tees.mad.stayfinder.data.repository.HotelRepository
import uk.ac.tees.mad.stayfinder.data.repository.HotelRepositoryImpl

class Container {
    val firebaseAuth : FirebaseAuth by lazy{
        FirebaseAuth.getInstance()
    }

   val fireStore : FirebaseFirestore by lazy {
       FirebaseFirestore.getInstance()
   }

    val apiService : HotelApiService by lazy {
        RetrofitClient.apiService
    }

    val authRepository : AuthRepository by lazy {
        AuthRepositoryImpl(
            auth = firebaseAuth,
            store = fireStore
        )
    }

    val hotelRepository : HotelRepository by lazy {
        HotelRepositoryImpl(apiService)
    }


}

/**
 * this is basically a dependency container since we are using manual dependency
 * we will create dependency here and will connect to application class by creating object
 * of container and in this way all instances lifecycle will be attached to the application
 * class lifecycle
 */