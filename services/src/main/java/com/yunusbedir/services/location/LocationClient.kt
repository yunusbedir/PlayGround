package com.yunusbedir.services.location

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationClient {

    fun getLocationUpdates(interval: Long): Flow<Location>

    class LocationException(override val message: String) : Exception()
}
