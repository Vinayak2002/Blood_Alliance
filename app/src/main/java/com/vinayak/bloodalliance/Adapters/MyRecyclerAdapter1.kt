package com.vinayak.bloodalliance.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vinayak.bloodalliance.R

class MyRecyclerAdapter1(private val context: Context, private val _id: MutableList<Int>,
                         private val bg: MutableList<String>, private val city: MutableList<String>,
                         private val state: MutableList<String>, private val name: MutableList<String>):
    RecyclerView.Adapter<MyRecyclerAdapter1.ViewHolderA>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderA {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_custom, parent, false)

        return ViewHolderA(view)
    }

    override fun onBindViewHolder(holder: ViewHolderA, position: Int) {
        holder._idText.text = _id[position].toString()
        holder.bgText.text = bg[position]
        holder.cityText.text = city[position]
        holder.stateText.text = state[position]
        holder.txtUserName.text = name[position]
    }

    override fun getItemCount(): Int {
        return state.size
    }

    class ViewHolderA(view: View): RecyclerView.ViewHolder(view) {
        val _idText: TextView = view.findViewById(R.id.txtUserID)
        val bgText: TextView = view.findViewById(R.id.txtUserBG)
        val cityText: TextView = view.findViewById(R.id.txtUserCity)
        val stateText: TextView = view.findViewById(R.id.txtUserState)
        val txtUserName: TextView = view.findViewById(R.id.txtUserName)
    }


}
