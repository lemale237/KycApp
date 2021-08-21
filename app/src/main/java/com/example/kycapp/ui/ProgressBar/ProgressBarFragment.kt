package com.example.kycapp.ui.ProgressBar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.kycapp.R

class ProgressBarFragment : Fragment() {

    companion object {
        fun newInstance() = ProgressBarFragment()
    }

    lateinit var progressBar:ProgressBar

    private lateinit var viewModel: ProgressBarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         var root= inflater.inflate(R.layout.progress_bar_fragment, container, false)
        progressBar= root.findViewById(R.id.progressBar)
        return  root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProgressBarViewModel::class.java)
        // TODO: Use the ViewModel
        Handler().postDelayed({
                              progressBar.visibility=View.GONE
        },2500)
    }

}