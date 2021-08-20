package com.example.kycapp.ui.SupprimerInfos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kycapp.R

class SupprimerInfosFragment : Fragment() {

    companion object {
        fun newInstance() = SupprimerInfosFragment()
    }

    private lateinit var viewModel: SupprimerInfosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.supprimer_infos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SupprimerInfosViewModel::class.java)
        // TODO: Use the ViewModel
    }

}