package com.example.kycapp.ui.successRegistration

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kycapp.R
import android.os.Handler
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.kycapp.ui.LoginActivity
import com.example.kycapp.ui.dashboard.DashboardFragment


class SucessRegistrationFragment : Fragment() {


    companion object {
        fun newInstance() = SucessRegistrationFragment()
    }

    private lateinit var viewModel: SucessRegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sucess_registration_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SucessRegistrationViewModel::class.java)
        // TODO: Use the ViewModel
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
         findNavController().navigate(R.id.action_sucessRegistrationFragment_to_navigation_dashboard)
        }, 3000)


    }



}