package uk.ac.tees.mad.stayfinder.data.repository

import androidx.compose.ui.util.unpackInt1
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await


const val USERS_COLLECTION = "users"

class AuthRepositoryImpl (
    private val auth : FirebaseAuth,
    private val store : FirebaseFirestore
)
    : AuthRepository {
    override suspend fun registerUser(
        email: String,
        password: String
    ): Result<Unit> {
        return try{
            val result = auth
                .createUserWithEmailAndPassword(email , password)
                .await()

            val user = result.user ?: throw Exception("User creation failed")

            val userData = mapOf(
                "uid" to user.uid,
                "email" to user.email,
                "createdAt" to System.currentTimeMillis()
            )

            try {
                store
                    .collection(USERS_COLLECTION)
                    .document(user.uid)
                    .set(userData)
                    .await()
            } catch (e: Exception) {
                user.delete().await()
                throw e
            }

            Result.success(Unit)
        }catch (e : Exception){
            Result.failure(e)
        }
    }

    override suspend fun loginUser(
        email: String,
        password: String
    ): Result<Unit> {
        return try {
                 auth
                .signInWithEmailAndPassword(email , password)
                .await()
            Result.success(Unit)
        }catch (e : Exception){
            Result.failure(e)
        }
    }

    override fun logout() :Result<Unit> {
        return  try {
            auth.signOut()
            Result.success(Unit)
        }catch (e : Exception){
            Result.failure(e)
        }
    }
}


/**
 * Register , Login , Logout --->>
 * these three function are written for the authentication of user
 * login with email and password for now verification of user for now
 */