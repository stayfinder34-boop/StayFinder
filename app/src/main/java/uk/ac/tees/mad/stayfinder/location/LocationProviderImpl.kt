package uk.ac.tees.mad.stayfinder.location

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class LocationProviderImpl(private val context: Context)
    : LocationProvider {
        private val fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    override suspend fun getLocation(): Pair<Double, Double>? {

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
                                    Pair(currentLocation.latitude, currentLocation.longitude)
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
}

/**
 * this class is location provider class it will provide the current location of device
 * Using Google’s Fused Location API
 * fused location provider --Google’s smart location engine
 * Combines GPS + WiFi + Mobile network
 * it is most accurate and battery efficient
 * it will provide pair<latitude , longitude>
 * this will give the last cached location if not available then fetch the latest
 **/