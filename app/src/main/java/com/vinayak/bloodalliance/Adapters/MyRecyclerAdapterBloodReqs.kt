package com.vinayak.bloodalliance.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vinayak.bloodalliance.R

class MyRecyclerAdapterBloodReqs(context: Context, private var rId: MutableList<Int>, private var uId: MutableList<Int>,
                                 private var bg: MutableList<String>, private var city: MutableList<String>,
                                 private var state: MutableList<String>)
    : RecyclerView.Adapter<MyRecyclerAdapterBloodReqs.ViewHolderY>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderY {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.blood_custom, parent, false)

        return ViewHolderY(view)
    }

    override fun onBindViewHolder(holder: ViewHolderY, position: Int) {
        holder.txtReq.text = rId[position].toString()
        holder.txtUserID.text = uId[position].toString()
        holder.txtUserBG.text = bg[position]
        holder.txtUserCity.text = city[position]
        holder.txtUserState.text = state[position]
    }

    override fun getItemCount(): Int {
        return city.size
    }

    class ViewHolderY(view: View) : RecyclerView.ViewHolder(view) {
        val txtReq: TextView = view.findViewById(R.id.txtReq)
        val txtUserID: TextView = view.findViewById(R.id.txtUserID)
        val txtUserBG: TextView = view.findViewById(R.id.txtUserBG)
        val txtUserCity: TextView = view.findViewById(R.id.txtUserCity)
        val txtUserState: TextView = view.findViewById(R.id.txtUserState)
    }

}
