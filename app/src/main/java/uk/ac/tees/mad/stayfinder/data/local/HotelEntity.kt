package uk.ac.tees.mad.stayfinder.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("hotel_table")
data class HotelEntity(
    @PrimaryKey
    val id : Long ,
    val name: String,
    val rating: Double?,
    val location : String ,
    val ratingText: String?,
    val imageUrl: String?,
    val price: Double?,
    val currency: String?,
    val latitude: Double,
    val longitude: Double ,
    val createdAt : Long = System.currentTimeMillis()
)