package com.example.kycapp.api

import com.example.kycapp.MainActivity
import com.example.kycapp.entites.Agent

class AgentApi {
    val db = MainActivity.db
    fun listAgent(onSuccess: (agents: List<Agent>) -> Unit, onFailure: (message: String) -> Unit) {
        db.collection("Agents").get().addOnSuccessListener {
            if (it.isEmpty) {

            } else {
                try {
                    var data = it.toObjects(Agent::class.java)
                    onSuccess(data)
                }catch (e:Exception){
                    if (e.message==null){
                        onFailure("failed to get the agents")
                    }else{
                        onFailure(e.message!!)
                    }

                }

            }
        }
    }

    fun createAgent(agent: Agent, onSuccess: () -> Unit, onFailure: (message: String) -> Unit) {
        db.collection("Agents").document().set(agent).addOnSuccessListener {
            onSuccess()
        }.addOnFailureListener {
            if (it.message == null) {
                onFailure("failed to save the agent try again later")
            } else {
                onFailure("failed to save the agent: ${it.message}")

            }

        }

    }


}