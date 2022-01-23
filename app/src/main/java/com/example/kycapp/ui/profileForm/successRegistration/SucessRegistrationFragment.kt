package com.example.kycapp.ui.profileForm.successRegistration

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kycapp.R
import android.os.Handler
import androidx.navigation.fragment.findNavController
import com.example.kycapp.ui.profileForm.HomeFragment


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
        HomeFragment.generalPosition.value?.let {
            HomeFragment.generalPosition.value = 0
        }
        Handler().postDelayed({
            try {
                findNavController().navigate(R.id.action_sucessRegistrationFragment_to_navigation_dashboard)
            } catch (e:Exception){

            }

        }, 3000)


    }



}