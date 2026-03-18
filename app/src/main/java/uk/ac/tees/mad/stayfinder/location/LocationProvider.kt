package uk.ac.tees.mad.stayfinder.location

interface LocationProvider {
    suspend fun getLocation(): Pair<Double, Double>?
}