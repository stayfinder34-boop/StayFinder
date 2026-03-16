package uk.ac.tees.mad.stayfinder.data.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HotelSearchResponse(
    val status: Boolean,
    val data: HotelData
)

@Serializable
data class HotelData(
    val result: List<HotelDto>
)


@Serializable
data class HotelDto(

    @SerialName("hotel_id")
    val hotelId: Long,

    @SerialName("hotel_name")
    val hotelName: String,

    @SerialName("review_score")
    val reviewScore: Double? = null,

    @SerialName("review_score_word")
    val reviewScoreWord: String? = null,

    val city: String,

    @SerialName("main_photo_url")
    val mainPhotoUrl: String? = null,

    @SerialName("min_total_price")
    val minTotalPrice: Double? = null,

    @SerialName("currencycode")
    val currencyCode: String? = null,

    val latitude: Double,
    val longitude: Double
)
