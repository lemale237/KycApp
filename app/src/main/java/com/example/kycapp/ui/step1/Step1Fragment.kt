package com.example.kycapp.ui.step1

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.example.kycapp.MainActivity
import com.example.kycapp.R

class Step1Fragment : Fragment() {

    companion object {
        fun newInstance() = Step1Fragment()
    }

    private lateinit var viewModel: Step1ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.step1_fragment, container, false)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Step1ViewModel::class.java)
        // TODO: Use the ViewModel
    }




}