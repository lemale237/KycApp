package com.example.kycapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kycapp.entites.Agent
import com.squareup.picasso.Picasso


class GridItemAdapter(private val agentList: ArrayList<Agent>) :
    RecyclerView.Adapter<GridItemAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardImages: ImageView = itemView.findViewById(R.id.cardImages)
        val cardTitle: TextView = itemView.findViewById(R.id.cardTitle)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.agent_item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cardTitle.text = agentList[position].agentName
        //Picasso.get().load(agentList[position].imageURL).into(holder.cardImages)
    }

    override fun getItemCount(): Int {
        return agentList.size

    }


}