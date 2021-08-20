package com.example.kycapp.ui.ModifierInfos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kycapp.R

class ModifierInfosFragment : Fragment() {

    companion object {
        fun newInstance() = ModifierInfosFragment()
    }

    private lateinit var viewModel: ModifierInfosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.modifier_infos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ModifierInfosViewModel::class.java)
        // TODO: Use the ViewModel
    }

}