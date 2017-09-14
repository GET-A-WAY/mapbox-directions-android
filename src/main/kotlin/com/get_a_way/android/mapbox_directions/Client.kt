package com.get_a_way.android.mapbox_directions

import com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
import com.fasterxml.jackson.databind.ObjectMapper
import com.get_a_way.android.mapbox_directions.Profile.driving
import com.mapbox.mapboxsdk.annotations.PolylineOptions
import com.mapbox.mapboxsdk.geometry.LatLng
import okhttp3.*
import java.io.IOException

/**
 * @author Niels Falk
 */
const val baseUrl = "https://api.mapbox.com/v4/directions/"

fun requestDirection(location: LatLng, target: LatLng, accessToken: String, profile: Profile = driving) =
        HttpUrl.parse("${baseUrl}mapbox.${profile}/${location.lngLat};${target.lngLat}.json")
                ?.newBuilder()
                ?.apply {
                    addQueryParameter("access_token", accessToken)
                    addQueryParameter("alternatives", "false")
                    addQueryParameter("steps", "false")
                }?.let {
            Request.Builder().url(it.build()).get()
        }


fun OkHttpClient.execute(requestDirection: Request.Builder?) =
        requestDirection?.let {
            newCall(it.build()).execute().parse()?.polyline
        }


fun OkHttpClient.enqueue(requestDirection: Request.Builder?, callback: PolylineOptions.() -> Unit) =
        requestDirection?.let {
            newCall(it.build()).enqueue(object : Callback {
                override fun onFailure(call: Call?, e: IOException?) {}

                override fun onResponse(call: Call?, response: Response?) {
                    response.parse()?.polyline?.let(callback)
                }
            })
        }

fun Response?.parse() = this?.let { it.body()?.parse<DirectionsResponse>() }

val objectMapper = ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false)

inline fun <reified T : Any?> ResponseBody.parse() = objectMapper.readValue(string(), T::class.java)

private val LatLng.lngLat: String get() = "$longitude,$latitude"