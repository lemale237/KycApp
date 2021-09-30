package com.example.kycapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kycapp.entites.Agent

class DashboardViewModel : ViewModel() {

   var selectedAgent=MutableLiveData<Agent>().apply {
       value=Agent()
   }
}