package uk.ac.tees.mad.stayfinder.utils

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import uk.ac.tees.mad.stayfinder.PreferenceManager
import uk.ac.tees.mad.stayfinder.data.local.HotelDataBase
import uk.ac.tees.mad.stayfinder.data.remote.HotelApiService
import uk.ac.tees.mad.stayfinder.data.repository.AuthRepository
import uk.ac.tees.mad.stayfinder.data.repository.AuthRepositoryImpl
import uk.ac.tees.mad.stayfinder.data.repository.HotelRepository
import uk.ac.tees.mad.stayfinder.data.repository.HotelRepositoryImpl
import uk.ac.tees.mad.stayfinder.location.LocationProvider
import uk.ac.tees.mad.stayfinder.location.LocationProviderImpl
import uk.ac.tees.mad.stayfinder.notification.ReminderScheduler

class Container(context : Context) {
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

    private val database: HotelDataBase by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            HotelDataBase::class.java,
            "hotel_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    val reminderScheduler by lazy {
        ReminderScheduler(context)
    }


    val hotelRepository : HotelRepository by lazy {
        HotelRepositoryImpl(
            apiService = apiService,
            dao = database.hotelDao() ,
            reminderScheduler = reminderScheduler
        )
    }

    val preferency by lazy {
        PreferenceManager(context)
    }

    val locationProvider : LocationProvider by lazy {
        LocationProviderImpl(context)
    }
    val dateProvider : DateProvider by lazy {
        DateProvider()
    }
}

/**
 * this is basically a dependency container since we are using manual dependency
 * we will create dependency here and will connect to application class by creating object
 * of container and in this way all instances lifecycle will be attached to the application
 * class lifecycle
 */