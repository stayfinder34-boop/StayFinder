package uk.ac.tees.mad.stayfinder.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Container {
    val firebaseAuth : FirebaseAuth by lazy{
        FirebaseAuth.getInstance()
    }

   val fireStore : FirebaseFirestore by lazy {
       FirebaseFirestore.getInstance()
   }

}

/**
 * this is basically a dependency container since we are using manual dependency
 * we will create dependency here and will connect to application class by creating object
 * of container and in this way all instances lifecycle will be attached to the application
 * class lifecycle
 */