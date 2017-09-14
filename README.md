# Mapbox directions android

an easy way to access mapbox directions api in kotlin

```
OkHttpClient().enqueue(requestDirection(location, target, ACCESS_TOKEN, walking)) {
    mapView.getMapAsync { map ->
        map.addPolyline(color(parseColor("#d3ff43")).width(5f))
    }
}
```