package com.example.kycapp.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kycapp.R
import com.example.kycapp.databinding.FragmentNotificationsBinding
import com.example.kycapp.ui.map.MapFragment
import com.example.kycapp.ui.map.MapViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class NotificationsFragment  : Fragment() {

    companion object {
        fun newInstance() = MapFragment()
    }

    private lateinit var viewModel: MapViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.map_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MapViewModel::class.java)
        val mapFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.map_fragment_map) as SupportMapFragment
        mapFragment.getMapAsync {
            // Add a marker in Sydney and move the camera
            val sydney = LatLng(-34.0, 151.0)
            it.addMarker(
                MarkerOptions()
                    .position(sydney)
                    .title("Marker in Sydney")
            )
            it.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        }
    }

}