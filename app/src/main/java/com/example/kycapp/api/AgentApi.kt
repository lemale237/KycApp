package com.example.kycapp.api

import com.example.kycapp.entites.Agent

class AgentApi {

    fun listAgent(): ArrayList<Agent>{


        return arrayListOf(

            Agent("", "Agent1"),
            Agent("", "Agent2"),
            Agent("", "Agent3"),
            Agent("", "Agent4"),
            Agent("", "Agent5"),
            Agent("", "Agent6"),
            Agent("", "Agent7"),
            Agent("", "Agent8"),
            Agent("", "Agent9"),
            Agent("", "Agent10")

        )
    }


}