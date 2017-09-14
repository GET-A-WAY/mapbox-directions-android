package com.get_a_way.android.mapbox_directions
import com.mapbox.mapboxsdk.geometry.LatLng
import com.winterbe.expekt.should
import okhttp3.OkHttpClient
import org.junit.Ignore
import org.junit.Test

/**
 * @author Niels Falk
 */
class ClientKtTest {
    private val okHttpClient: OkHttpClient = OkHttpClient()
    private val location = LatLng(52.536882, 13.395009)
    private val target = LatLng(52.531009, 13.400296)
    private val accessToken = System.getProperty("mapbox-access-token")


    @Test
    @Ignore
    fun requestpoliline() {
        val requestDirection = requestDirection(location, target, accessToken)
        val polyline = okHttpClient.execute(requestDirection)

        polyline?.points?.firstOrNull()?.latitude?.toInt().should.equal(52)
        polyline?.points?.lastOrNull()?.longitude?.toInt().should.equal(13)
    }
}


