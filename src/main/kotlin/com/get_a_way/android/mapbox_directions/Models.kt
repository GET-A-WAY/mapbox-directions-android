package com.get_a_way.android.mapbox_directions

import com.mapbox.mapboxsdk.annotations.PolylineOptions
import com.mapbox.mapboxsdk.geometry.LatLng

/**
 * @author Niels Falk
 */
class DirectionsResponse {
    var origin: DirectionsFeature? = null
    var destination: DirectionsFeature? = null
    var waypoints: List<DirectionsFeature>? = listOf()
    var routes: List<DirectionsRoute>? = listOf()
    private val latLngs by lazy { routes?.firstOrNull()?.geometry?.coordinates?.map { LatLng(it[1], it[0]) }?.toTypedArray() }
    val polyline get() = PolylineOptions().apply { latLngs?.let { add(*it) } }
}

class DirectionsFeature {
    var type: String? = null
    var geometry: FeatureGeometry? = null
    var properties: FeatureProperties? = null
}

class DirectionsRoute {
    var distance: Int = 0
    var duration: Int = 0
    var summary: String? = null
    var geometry: RouteGeometry? = null
    var steps: List<RouteStep>? = listOf()
}

class FeatureGeometry {
    var type: String? = null
    var coordinates: List<Double>? = listOf()
}

class FeatureProperties {
    var name: String? = null
}

class ManeuverPoint {
    var type: String? = null
    var coordinates: List<Double>? = listOf()
}

class RouteGeometry {
    var type: String? = null
    var coordinates: List<List<Double>>? = listOf()
}

class RouteStep {
    var distance: Int = 0
    var duration: Int = 0
    var way_name: String? = null
    var direction: String? = null
    var heading: Double = 0.toDouble()
    var maneuver: StepManeuver? = null
}

class StepManeuver {
    var type: String? = null
    var location: ManeuverPoint? = null
    var instruction: String? = null
    var mode: String? = null
}

enum class Profile {
    driving, walking, cycling
}
