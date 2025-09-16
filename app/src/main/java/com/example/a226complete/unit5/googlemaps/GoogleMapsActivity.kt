package com.example.a226complete.unit5.googlemaps

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.a226complete.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.Locale

class GoogleMapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val client = OkHttpClient()

    private lateinit var etAddress: EditText
    private lateinit var btnSearch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_google_maps)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        etAddress = findViewById(R.id.etAddress)
        btnSearch = findViewById(R.id.btnSearch)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        btnSearch.setOnClickListener {
            val address = etAddress.text.toString()
            if (address.isNotBlank()) {
                val latLng = getLatLngFromAddress(address)
                if (latLng != null) {
                    mMap.addMarker(MarkerOptions().position(latLng).title(address))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))
                    fetchWeather(latLng.latitude, latLng.longitude, address)
                } else {
                    Toast.makeText(this, "Address not found", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
        getUserLocation()
    }

    private fun getUserLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 101)
            return
        }

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                val latLng = LatLng(it.latitude, it.longitude)
                val address = getAddressFromLatLng(latLng)

                mMap.addMarker(MarkerOptions().position(latLng).title("You are here").snippet(address))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f))

                fetchWeather(it.latitude, it.longitude, address)
            }
        }
    }

    private fun getAddressFromLatLng(latLng: LatLng): String {
        return try {
            val geocoder = Geocoder(this, Locale.getDefault())
            val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            if (!addresses.isNullOrEmpty()) addresses[0].getAddressLine(0)
             else "Address not found"
        } catch (e: Exception) {
            e.printStackTrace()
            "Address lookup failed"
        }
    }

    private fun getLatLngFromAddress(address: String): LatLng? {
        return try {
            val geocoder = Geocoder(this, Locale.getDefault())
            val addresses = geocoder.getFromLocationName(address, 1)
            if (!addresses.isNullOrEmpty()) {
                val location = addresses[0]
                LatLng(location.latitude, location.longitude)
            } else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun fetchWeather(lat: Double, lng: Double, address: String) {
        val apiKey = "c3714d446c4d48d1910122040232903" // replace with your Weather API key
        val url = "https://api.weatherapi.com/v1/current.json?key=$apiKey&q=$lat,$lng"

        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread { Toast.makeText(this@GoogleMapsActivity, "Weather fetch failed", Toast.LENGTH_SHORT).show() }
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string()
                try {
                    val obj = JSONObject(json ?: "{}")
                    if (!obj.has("current")) return
                    val current = obj.getJSONObject("current")
                    val temp = current.optDouble("temp_c", Double.NaN)
                    val condition = current.optJSONObject("condition")?.optString("text", "N/A") ?: "N/A"

                    runOnUiThread {
                        val latLng = LatLng(lat, lng)
                        mMap.addMarker(MarkerOptions().position(latLng).title("You are here")
                            .snippet("$address\nWeather: $temp °C, $condition"))
                        Toast.makeText(this@GoogleMapsActivity, "Weather: $temp °C, $condition", Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }
}
