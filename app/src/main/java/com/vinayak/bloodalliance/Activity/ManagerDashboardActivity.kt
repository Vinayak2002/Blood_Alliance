package com.vinayak.bloodalliance.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.vinayak.bloodalliance.*

class ManagerDashboardActivity : AppCompatActivity() {

    private lateinit var txtWelcome: TextView
    private lateinit var btnShowUsers: Button
    private lateinit var btnShowStock: Button
    private lateinit var btnDonationReq: Button
    private lateinit var btnBloodReq: Button
    private lateinit var btnCreateManager: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_dashboard)

        var id = 0

        if (intent != null) {
            id = intent.getStringExtra("id").toString().toInt()
        } else {
            finish()
            Toast.makeText(this, "Some unexpected error occurred!!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ManagerLoginActivity::class.java)
            startActivity(intent)
        }

        val database = baseContext.openOrCreateDatabase("data1.db", Context.MODE_PRIVATE, null)

        txtWelcome = findViewById(R.id.txtWelcome)
        btnShowUsers = findViewById(R.id.btnShowUsers)
        btnShowStock = findViewById(R.id.btnShowStock)
        btnDonationReq = findViewById(R.id.btnDonationReq)
        btnBloodReq = findViewById(R.id.btnBloodReq)
        btnCreateManager = findViewById(R.id.btnCreateManager)

        btnShowUsers.setOnClickListener {
            val intent = Intent(this, ShowUsersActivity::class.java)
            startActivity(intent)
        }

        btnDonationReq.setOnClickListener {
            val intent = Intent(this, ShowDonationsActivity::class.java)
            startActivity(intent)
//            Toast.makeText(this, "This functionality is under development", Toast.LENGTH_SHORT).show()
        }

        btnBloodReq.setOnClickListener {
            val intent = Intent(this, ShowBldRequestsActivity::class.java)
            startActivity(intent)
//            Toast.makeText(this, "This functionality is under development", Toast.LENGTH_SHORT).show()
        }

        btnShowStock.setOnClickListener {
            val intent = Intent(this, ShowStockActivity::class.java)
            startActivity(intent)
        }

        btnCreateManager.setOnClickListener {
            Toast.makeText(this, "This functionality is under development", Toast.LENGTH_SHORT).show()
        }




    }
}