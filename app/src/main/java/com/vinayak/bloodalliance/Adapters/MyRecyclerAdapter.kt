package com.vinayak.bloodalliance.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.vinayak.bloodalliance.R

class MyRecyclerAdapter(private val context: Context, private val _id: MutableList<Int>,
                        private val bg: MutableList<String>, private val city: MutableList<String>,
                        private val state: MutableList<String>, private val exp: MutableList<String>):
    RecyclerView.Adapter<MyRecyclerAdapter.ViewHolderX>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderX {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom, parent, false)

        return ViewHolderX(view)
    }

    override fun onBindViewHolder(holder: ViewHolderX, position: Int) {
        holder._idText.text = _id[position].toString()
        holder.bgText.text = bg[position]
        holder.cityText.text = city[position]
        holder.stateText.text = state[position]
        holder.expText.text = exp[position]
    }

    override fun getItemCount(): Int {
        return state.size
    }

    class ViewHolderX(view: View): RecyclerView.ViewHolder(view) {
        val _idText: TextView = view.findViewById(R.id.txtID)
        val bgText: TextView = view.findViewById(R.id.txtBG)
        val cityText: TextView = view.findViewById(R.id.txtCity)
        val stateText: TextView = view.findViewById(R.id.txtState)
        val expText: TextView = view.findViewById(R.id.txtExp)
    }
}