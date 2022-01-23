package com.example.kycapp.ui.profileForm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kycapp.api.AgentApi
import com.example.kycapp.entites.Agent

/**
 * Home fragment view model
 *
 * @constructor Create empty Home fragment view model
 */
class HomeFragmentViewModel : ViewModel() {
    private val users: MutableLiveData<List<Agent>> by lazy {
        MutableLiveData<List<Agent>>().also {
            loadUsers()
        }
    }
    val api = AgentApi()
    var userToCreate = MutableLiveData<Agent>().apply {
        value = Agent()
    }


    fun getUsers(): LiveData<List<Agent>> {
        return users
    }
    fun createAgent(onSuccess:()->Unit,onError:(message:String)->Unit){
        api.createAgent(userToCreate.value!!,{
            onSuccess()
        },{
            onError(it)
        })
    }

    private fun loadUsers() {
        // Do an asynchronous operation to fetch users.
    }
}