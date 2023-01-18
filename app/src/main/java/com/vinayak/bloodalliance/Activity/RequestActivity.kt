package com.vinayak.bloodalliance.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.vinayak.bloodalliance.R

class RequestActivity : AppCompatActivity() {

    private lateinit var head: TextView
    private lateinit var etBloodGroup: EditText
    private lateinit var etCity: EditText
    private lateinit var etState: EditText
    private lateinit var btnSubmitReq: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)

        head = findViewById(R.id.head)
        etBloodGroup = findViewById(R.id.etBloodGroup)
        etCity = findViewById(R.id.etCity)
        etState = findViewById(R.id.etState)
        btnSubmitReq = findViewById(R.id.btnSubmitReq)

        var id = 0
        var req = ""

        if (intent != null) {
            id = intent.getStringExtra("id").toString().toInt()
            req = intent.getStringExtra("request").toString()
            val txt = "Request for: $req"
            head.text = txt
        } else {
            finish()
            Toast.makeText(this, "Some unexpected error occurred!!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnSubmitReq.setOnClickListener {
            val database = baseContext.openOrCreateDatabase("data1.db", Context.MODE_PRIVATE, null)

            if (req == "blood") {

                val sql = database.rawQuery( "INSERT INTO BLOOD_REQ (REQUEST_BY, REQUEST_DATE, CITY, STATE, STATUS, BLDGRP) VALUES(?, DATE('now'), ?, ?, ?, ?)",
                arrayOf(id.toString(), etCity.text.toString(), etState.text.toString(), "N", etBloodGroup.text.toString()))
                sql.moveToFirst()
                sql.close()
                Toast.makeText(this, "Request for Blood Successfully Placed", Toast.LENGTH_SHORT).show()

            } else {


                val sql = database.rawQuery("INSERT INTO DONATION_REQ (REQUEST_BY, REQUEST_DATE, BLDGRP, CITY, STATE, STATUS) VALUES(?, DATE('now'), ?, ?, ?, ?)",
                arrayOf(id.toString(), etBloodGroup.text.toString(), etCity.text.toString(), etState.text.toString(), "N" ))
                sql.moveToFirst()
                sql.close()
                Toast.makeText(
                    this,
                    "Request for Donation Successfully Placed",
                    Toast.LENGTH_SHORT
                ).show()
            }
            val intent = Intent(this, DashboardActivity::class.java)
            intent.putExtra("id", id.toString())
            startActivity(intent)
        }
    }
}