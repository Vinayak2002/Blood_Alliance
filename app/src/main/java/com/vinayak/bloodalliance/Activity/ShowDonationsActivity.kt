package com.vinayak.bloodalliance.Activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vinayak.bloodalliance.Adapters.MyRecyclerAdapterDonations
import com.vinayak.bloodalliance.R

class ShowDonationsActivity : AppCompatActivity() {

    private var RId = mutableListOf<Int>()
    private var UId = mutableListOf<Int>()
    private var  bg = mutableListOf<String>()
    private var  city = mutableListOf<String>()
    private var  state = mutableListOf<String>()

    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_donations)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val database = baseContext.openOrCreateDatabase("data1.db", Context.MODE_PRIVATE, null)

        val query = database.rawQuery("SELECT id, request_by, bldgrp, city, state FROM DONATION_REQ", null)
        query.use {
            while (it.moveToNext()) {
                RId.add(it.getInt(0))
                UId.add(it.getInt(1))
                bg.add(it.getString(2))
                city.add(it.getString(3))
                state.add(it.getString(4))
            }
            it.close()
        }
        database.close()

        val myRecyclerAdapter = MyRecyclerAdapterDonations(this, RId, UId, bg, city, state)
        recyclerView.adapter = myRecyclerAdapter
    }
}