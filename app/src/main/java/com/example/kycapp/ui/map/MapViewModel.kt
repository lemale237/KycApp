package com.example.kycapp.ui.map

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.RoomDatabase
import com.example.kycapp.entites.remote.Location
import com.example.kycapp.GozemApplication
import com.example.kycapp.api.AgentApi
import com.example.kycapp.entites.Agent
import kotlinx.coroutines.launch

class MapViewModel() : ViewModel() {
   fun insertData(location: Location,context: Context){
      viewModelScope.launch {
         val api = AgentApi()

         api.createLocation(location,{

         },onFailure = {
            Toast.makeText(context,it,Toast.LENGTH_LONG).show()
         })

      }
   }
   var locationsLiveData=MutableLiveData<List<Location>>().apply {
      value= mutableListOf()
   }
   fun getAllLocation(context: Context){
      val api = AgentApi()

      api.lisLocations({
                       locationsLiveData.postValue(it)

      },onFailure = {
         Toast.makeText(context,it,Toast.LENGTH_LONG).show()
      })


   }
   var agent=MutableLiveData<Agent>().apply {
      value= Agent()
   }
    fun getAgent(context: Context){
      val api = AgentApi()
      api.getAgent("+237698481557",{
         agent.postValue(it)
      },{
         Toast.makeText(context,it,Toast.LENGTH_LONG).show()
      })
   }
}