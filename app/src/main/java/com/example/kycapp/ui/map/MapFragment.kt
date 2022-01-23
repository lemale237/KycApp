package com.example.kycapp.ui.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Location
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.kycapp.MainActivity
import com.example.kycapp.R
import com.example.kycapp.utils.utils
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.map_fragment.*
import kotlinx.coroutines.launch
import java.util.*

class MapFragment : Fragment() {

    companion object {
        fun newInstance() = MapFragment()
    }

    private lateinit var viewModel: MapViewModel
    var mapFragment: SupportMapFragment? = null
    var locations = mutableListOf<Location>()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.map_fragment, container, false)
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    // Precise location access granted.
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    // Only approximate location access granted.
                }
                else -> {
                    // No location access granted.
                }
            }
        }
        // ...

// Before you perform the actual permission request, check whether your app
// already has the permissions, and whether your app needs to show a permission
// rationale dialog. For more details, see Request permissions.
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        mapFragment =
            childFragmentManager.findFragmentById(R.id.map_fragment_map) as SupportMapFragment?  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment!!.getMapAsync { mMap ->
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

            mMap.clear() //clear old markers
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

            } else {
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location: Location? ->
                        val googlePlex = CameraPosition.builder()
                            .target(LatLng(location!!.latitude, location.longitude))
                            .zoom(10f)
                            .bearing(0f)
                            .tilt(45f)
                            .build()
                        mMap.animateCamera(
                            CameraUpdateFactory.newCameraPosition(googlePlex),
                            10000,
                            null
                        )

                        mMap.addMarker(
                            MarkerOptions()
                                .position(LatLng(location.latitude, location.longitude))
                                .title("Spider Man")
                                .icon(bitmapDescriptorFromVector(activity, R.drawable.spider))
                        )

                        /*  mMap.addMarker(
                               MarkerOptions()
                      /*mMap.addMarker(
                               MarkerOptions()
                                   .position(LatLng(37.4219999, -122.0862462))
                                   .title("Spider Man")
                                   .icon(bitmapDescriptorFromVector(activity, R.drawable.spider))
                           )

                           mMap.addMarker(
                               MarkerOptions()
                                   .position(LatLng(37.4629101, -122.2449094))
                                   .title("Iron Man")
                                   .snippet("His Talent : Plenty of money")
                           )*/             .position(LatLng(37.4629101, -122.2449094))
                                   .title("Iron Man")
                                   .snippet("His Talent : Plenty of money")
                           )*/
                    }


                mMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(37.3092293, -122.1136845))
                        .title("Captain America")
                )


            }

        }

        return rootView
    }



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MapViewModel::class.java)
        getLastLocation()
        viewModel.getAllLocation(requireContext())
        initLocations()
        viewModel.getAgent(requireContext())
        viewModel.agent.observe(viewLifecycleOwner, Observer {
            user_name.setText(it.agentName)
            user_email.setText(it.phoneNumber)
            Log.e("Gozem",it.toString())
            if(it.photoAgent!=null && it.photoAgent!="" ){
               // Picasso.get().load(it.photoAgent).error(R.drawable.ic_baseline_broken_image_24).into(profileImage)
                Glide.with(requireContext()).load(it.photoAgent).circleCrop().error(R.drawable.ic_baseline_broken_image_24).into(profileImage)
            }


        })


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initLocations() {
        viewModel.locationsLiveData.observe(viewLifecycleOwner, Observer { locations ->
            Log.e("GOzemLocations", locations.toString())
            if (locations != null) {
                locations?.forEach { location ->
                    mapFragment!!.getMapAsync { mMap ->
                        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                        mMap.addMarker(
                            MarkerOptions()
                                .position(
                                    LatLng(
                                        location.latitude + (0..3).random(),
                                        location.longitude
                                    )
                                )
                                .title(location.date.toString())
                        )
                    }

                }
            }
        })
    }


    private fun getLastLocation(
    ) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        } else {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        viewModel.insertData(
                            com.example.kycapp.entites.remote.Location(
                                location.latitude,
                                location.longitude,
                                Date()
                            ), requireContext()
                        )
                    }
                }
        }

    }


    private fun bitmapDescriptorFromVector(context: Context?, vectorResId: Int): BitmapDescriptor {
        val vectorDrawable = ContextCompat.getDrawable(requireContext(), vectorResId)
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )
        val bitmap =
            Bitmap.createBitmap(
                vectorDrawable.intrinsicWidth,
                vectorDrawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }


}