package com.example.kycapp.ui.successRegistration

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kycapp.R

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
    }

}