package uk.ac.tees.mad.stayfinder.data.model


data class Hotel(
    val id: Long,
    val name: String,
    val rating: Double?,
    val ratingText: String?,
    val imageUrl: String?,
    val price: Double?,
    val currency: String?,
    val latitude: Double,
    val longitude: Double
)