package com.vinayak.bloodalliance.Activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vinayak.bloodalliance.Adapters.MyRecyclerAdapter1
import com.vinayak.bloodalliance.R

class ShowUsersActivity : AppCompatActivity() {

    private var _id = mutableListOf<Int>()
    private var  bg = mutableListOf<String>()
    private var  city = mutableListOf<String>()
    private var  state = mutableListOf<String>()
    private var  name = mutableListOf<String>()

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_users)

        recyclerView = findViewById(R.id.listView1)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val database = baseContext.openOrCreateDatabase("data1.db", Context.MODE_PRIVATE, null)

        val query = database.rawQuery("SELECT id, bldgrp, city, state, name FROM USERS", null)
        query.use {
            while (it.moveToNext()) {
                _id.add(it.getInt(0))
                bg.add(it.getString(1))
                city.add(it.getString(2))
                state.add(it.getString(3))
                name.add(it.getString(4))
            }
            it.close()
        }
        database.close()

        val myRecyclerAdapter = MyRecyclerAdapter1(this, _id, bg, city, state, name)
        recyclerView.adapter = myRecyclerAdapter
    }
}