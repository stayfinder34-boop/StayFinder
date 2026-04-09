package uk.ac.tees.mad.stayfinder.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class DestinationResponse(

    @SerialName("status")
    val status: Boolean ? = null,

    @SerialName("data")
    val data: List<DestinationDto> ? = null,

    @SerialName("message")
    val message: String? = null
)

@Serializable
data class DestinationDto(

    @SerialName("dest_id")
    val destId: String,

    @SerialName("search_type")
    val searchType: String,

    @SerialName("name")
    val name: String,

    @SerialName("label")
    val label: String,

    @SerialName("dest_type")
    val destType: String,

    @SerialName("latitude")
    val latitude: Double? = null,

    @SerialName("longitude")
    val longitude: Double? = null
)