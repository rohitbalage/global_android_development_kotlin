import android.graphics.Color
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.delay

class Shapes {
    val cleveland = LatLng(41.49878830382801, -81.67565041048111)
    val akron = LatLng(41.0844671, -81.5953782)
    val washingtondc = LatLng(38.8938592, -77.0969764)
    val columbus = LatLng(39.9830978, -83.1556356)
    val detroit = LatLng(42.3528084, -83.1816053)
    val pittsburg = LatLng(40.4314699, -80.0629009)
    val losangeles = LatLng(34.0206085, -118.7413725)
    val sanfransico = LatLng(37.7577607, -122.4787995)
    val losvegas = LatLng(36.1251645, -115.3398072)
    val toronto = LatLng(43.718371,-79.542864)
    val newyork = LatLng(40.6976312, -74.1444874)
    private val riodeJanero = LatLng(-22.9137295, -43.61079)
    val atlanta = LatLng(33.7674828, -84.5025311)
    val raleigh = LatLng(35.8439338, -78.8098631)
    val miami = LatLng(25.7825389, -80.3118593)
    val austin = LatLng(30.3079541, -97.9205504)
    val madrid = LatLng(40.4380986, -3.8443431)
    val colarado = LatLng(38.9724786, -108.1904446)
    val oklahoma = LatLng(35.2848492, -101.3565999)
    val dallas = LatLng(32.8208451, -96.8963602)
    val minneapolis = LatLng(44.9707888, -93.3438787)
    val saltlake = LatLng(40.7767086, -112.0028854)
    val stlouis = LatLng(38.6532056, -90.3258624)
    val chicago = LatLng(41.833871, -87.8967702)
    val kansas = LatLng(38.4727673, -100.9597019)
    val seattle = LatLng(47.6088285, -122.5046032)
    val boston = LatLng(42.3144474, -71.0526843)

    suspend fun addPolyline(map: GoogleMap) {
        val polyline = map.addPolyline(
            PolylineOptions().apply {
                add(losangeles, newyork, madrid)
                color(Color.BLUE)
                width(5f)
                geodesic(true)  // add arcs in polyline
                clickable(true) // set click on Polyline
            }
        )

        // return type here is polyline object so we can change this at last moment
        // change polyline after 5 seconds
        delay(5000L)

        val newList = listOf(losangeles, riodeJanero, madrid)

        polyline.points = newList
    }

    fun addPolygon(map: GoogleMap) {
        val polygon = map.addPolygon(
            PolygonOptions().apply {
                add(detroit, toronto, columbus, atlanta, raleigh)
                strokeColor(Color.argb(128, 0, 0, 255)) // 50% transparent blue
                fillColor(Color.argb(128, 0, 255, 255)) // 50% transparent cyan
                strokeWidth(5f)
                geodesic(true)  // add arcs in polygon
                clickable(true) // set click on Polygon
            }
        )
    }
}
