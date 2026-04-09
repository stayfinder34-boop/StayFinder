package uk.ac.tees.mad.stayfinder.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uk.ac.tees.mad.stayfinder.utils.Converters

@Database(
    entities = [HotelEntity::class],
    version = 1
)

@TypeConverters(Converters::class)

abstract class HotelDataBase : RoomDatabase() {
    abstract fun hotelDao() : HotelDao
}