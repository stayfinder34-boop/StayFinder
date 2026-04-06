package uk.ac.tees.mad.stayfinder.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface HotelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHotels(hotels: List<HotelEntity>)

    @Query("SELECT * FROM hotel_table WHERE id = :id")
     fun getHotelById(id: Long): HotelEntity?

     @Query("SELECT * FROM hotel_table")
     fun getAllHotels(): Flow<List<HotelEntity>>

     @Query("DELETE FROM hotel_table where destId != :id")
     suspend fun deleteAllHotels(id : String)
}
