package com.example.kycapp.ui.ProgressBar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kycapp.R

class ProgressBarFragment : Fragment() {

    companion object {
        fun newInstance() = ProgressBarFragment()
    }

    private lateinit var viewModel: ProgressBarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.progress_bar_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProgressBarViewModel::class.java)
        // TODO: Use the ViewModel
    }

}