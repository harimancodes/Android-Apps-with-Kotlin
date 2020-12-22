package com.example.pokemon

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.*

import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Exception

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null
    private val ACCESSLOCATION = 123
    private var pokemonIsLoaded: Boolean? = false
    private var playerPower: Double = 0.0
    private var location: Location? = null
    private var gameStarted: Boolean? = false
    private var listOfPokemons = ArrayList<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        checkPermission()

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    ACCESSLOCATION
                )
                return
            }
        }
        getUserLocation()
    }

    private fun getUserLocation() {
        Toast.makeText(this, "User location acces on!", Toast.LENGTH_SHORT).show()
        val myLocation = MyLocationListener()
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3, 3f, myLocation)
        val mythread = MyThread()
        mythread.start()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            ACCESSLOCATION -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getUserLocation()
                } else {
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    inner class MyLocationListener : LocationListener {

        init {
            location = Location("Start")
            location!!.latitude = 0.0
            location!!.longitude = 0.0
        }

        override fun onLocationChanged(loc: Location?) {
            location = loc
            if (!pokemonIsLoaded!!) loadPokemon()

        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        }

        override fun onProviderEnabled(provider: String?) {
        }

        override fun onProviderDisabled(provider: String?) {
        }

    }

    var oldLocation: Location? = null

    inner class MyThread : Thread() {
        init {
            oldLocation = Location("Start")
            oldLocation!!.latitude = 0.0
            oldLocation!!.longitude = 0.0
        }

        override fun run() {
            while (true) {
                try {
                    if (oldLocation!!.distanceTo(location) == 0f) {
                        continue
                    }
                    oldLocation = location
                    runOnUiThread {
                        mMap!!.clear()
                        val currentLocation = LatLng(location!!.latitude, location!!.longitude)
                        mMap!!.addMarker(
                            MarkerOptions().position(currentLocation).title("You")
                                .snippet("Power : $playerPower")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mario))
                        )
                        if (!gameStarted!!) {
                            mMap!!.moveCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                    currentLocation,
                                    14f
                                )
                            )
                            gameStarted = true
                        }
                        //show pockemons
                        for (i in 0 until listOfPokemons.size) {
                            val newPokemon = listOfPokemons[i]
                            if (newPokemon.isCatch == false) {
                                val pokemon = LatLng(
                                    newPokemon.location!!.latitude,
                                    newPokemon.location!!.longitude
                                )
                                mMap!!.addMarker(
                                    MarkerOptions().position(pokemon).title(newPokemon.name!!)
                                        .snippet("${newPokemon.des!!}, Power : ${newPokemon.power}")
                                        .icon(BitmapDescriptorFactory.fromResource(newPokemon.image!!))
                                )
                                if (!pokemonIsLoaded!!) mMap!!.moveCamera(
                                    CameraUpdateFactory.newLatLngZoom(
                                        pokemon,
                                        14f
                                    )
                                )

                                if (location!!.distanceTo(newPokemon.location) < 9) {
                                    newPokemon.isCatch = true
                                    listOfPokemons[i] = newPokemon
                                    playerPower += newPokemon.power!!
                                    Toast.makeText(
                                        applicationContext,
                                        "You have catched ${newPokemon.name!!}.\nYour new power is $playerPower",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                    }
                    sleep(1000)
                } catch (ex: Exception) {
                }
            }
        }
    }

    fun loadPokemon() {
        Log.i("current coordinates", "${location!!.latitude},${location!!.longitude}")
        listOfPokemons.add(
            Pokemon(
                "Charmander",
                "Japanese",
                R.drawable.charmander,
                55.0,
                location!!.latitude + 0.001193,
                location!!.longitude + 0.000194,
                false
            )
        )
        listOfPokemons.add(
            Pokemon(
                "Bulbasaur",
                "Russian",
                R.drawable.bulbasaur,
                85.0,
                location!!.latitude - 0.001313,
                location!!.longitude - 0.000394,
                false
            )
        )
        listOfPokemons.add(
            Pokemon(
                "Squirtle",
                "Chinese",
                R.drawable.squirtle,
                73.0,
                location!!.latitude + 0.001553,
                location!!.longitude + 0.000594,
                false
            )
        )
        pokemonIsLoaded = true
    }
}


