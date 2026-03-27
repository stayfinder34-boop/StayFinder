package uk.ac.tees.mad.stayfinder.data.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class HotelSearchResponse(

    @SerialName("status")
    val status: Boolean,

    @SerialName("data")
    val data: HotelData
)

@Serializable
data class HotelData(

    @SerialName("hotels")
    val hotels: List<HotelDto>
)

@Serializable
data class HotelDto(

    @SerialName("hotel_id")
    val hotelId: Long,

    @SerialName("property")
    val property: PropertyDto
)

@Serializable
data class PropertyDto(

    @SerialName("name")
    val name: String,

    @SerialName("reviewScore")
    val reviewScore: Double? = null,

    @SerialName("reviewScoreWord")
    val reviewScoreWord: String? = null,

    @SerialName("latitude")
    val latitude: Double,

    @SerialName("longitude")
    val longitude: Double,

    @SerialName("photoUrls")
    val photoUrls: List<String>? = null,

    @SerialName("priceBreakdown")
    val priceBreakdown: PriceBreakdownDto? = null
)


@Serializable
data class PriceBreakdownDto(

    @SerialName("grossPrice")
    val grossPrice: GrossPriceDto? = null
)


@Serializable
data class GrossPriceDto(

    @SerialName("value")
    val value: Double,

    @SerialName("currency")
    val currency: String
)