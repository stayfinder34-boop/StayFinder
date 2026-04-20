package uk.ac.tees.mad.stayfinder.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.os.Build
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.Locale
import kotlin.coroutines.resume

class LocationProviderImpl(
    private val context: Context
) : LocationProvider {
    private val fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    override suspend fun getLocation(): String? {

        val latLng = getLatLng() ?: return null

        return reverseGeocode(latLng.first, latLng.second)
    }

    @SuppressLint("MissingPermission")
    private suspend fun getLatLng(): Pair<Double, Double>? {

        return suspendCancellableCoroutine { continuation ->

            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->

                    if (location != null) {
                        continuation.resume(
                            Pair(location.latitude, location.longitude)
                        )
                    } else {
                        fusedLocationClient.getCurrentLocation(
                            com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY,
                            null
                        ).addOnSuccessListener { currentLocation ->

                            if (currentLocation != null) {
                                continuation.resume(
                                    Pair(
                                        currentLocation.latitude,
                                        currentLocation.longitude
                                    )
                                )
                            } else {
                                continuation.resume(null)
                            }
                        }.addOnFailureListener {
                            continuation.resume(null)
                        }
                    }
                }
                .addOnFailureListener {
                    continuation.resume(null)
                }
        }
    }

    private suspend fun reverseGeocode(
        latitude: Double,
        longitude: Double
    ): String? {

        val geocoder = Geocoder(context, Locale.getDefault())

        return suspendCancellableCoroutine { continuation ->

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                geocoder.getFromLocation(
                    latitude,
                    longitude,
                    1
                ) { addresses ->

                    val city = addresses.firstOrNull()?.locality
                    continuation.resume(city)
                }

            } else {

                val addresses =
                    geocoder.getFromLocation(latitude, longitude, 1)

                val city = addresses?.firstOrNull()?.locality
                continuation.resume(city)
            }
        }
    }
}