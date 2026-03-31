package uk.ac.tees.mad.stayfinder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [HotelEntity::class],
    version = 1
)

abstract class HotelDataBase : RoomDatabase() {
    abstract fun hotelDao() : HotelDao
}