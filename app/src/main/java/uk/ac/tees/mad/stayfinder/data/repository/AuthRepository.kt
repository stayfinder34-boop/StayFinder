package uk.ac.tees.mad.stayfinder.data.repository

interface AuthRepository {
    suspend fun registerUser(email : String , password : String) : Result<Unit>
   suspend fun loginUser(email : String , password : String) : Result<Unit>
    fun logout():Result<Unit>
}
