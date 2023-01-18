package com.vinayak.bloodalliance.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.vinayak.bloodalliance.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var txtWelcome: TextView
    private lateinit var btnShowProfile: Button
    private lateinit var btnShowStock: Button
    private lateinit var btnPutDonationReq: Button
    private lateinit var btnPutBloodReq: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        txtWelcome = findViewById(R.id.txtWelcome)
        btnShowProfile = findViewById(R.id.btnShowProfile)
        btnShowStock = findViewById(R.id.btnShowStock)
        btnPutDonationReq = findViewById(R.id.btnPutDonationReq)
        btnPutBloodReq = findViewById(R.id.btnPutBloodReq)

        var id = 0

        if (intent != null) {
            id = intent.getStringExtra("id")?.toInt() ?: 1001
        } else {
            finish()
            Toast.makeText(this, "Some unexpected error occurred!!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val database = baseContext.openOrCreateDatabase("data1.db", Context.MODE_PRIVATE, null)

        val query = database.rawQuery("SELECT name FROM USERS WHERE id = ?",
            arrayOf(id.toString())
        )
        query.use {
            if (it.moveToFirst()) {
                with(it) {
                    val name = getString(0)
                    val txt = "Welcome $name"
                    txtWelcome.text = txt
                }
            }
        }

        database.close()

        btnShowProfile.setOnClickListener {
            val intent = Intent(this, ProfileViewActivity::class.java)
            intent.putExtra("role", "user")
            intent.putExtra("id", id.toString())
            startActivity(intent)
        }

        btnShowStock.setOnClickListener {
            val intent = Intent(this, ShowStockActivity::class.java)
            intent.putExtra("role", "user")
            intent.putExtra("id", id.toString())
            startActivity(intent)
        }

        btnPutDonationReq.setOnClickListener {
            val intent = Intent(this, RequestActivity::class.java)
            intent.putExtra("request", "donation")
            intent.putExtra("id", id.toString())
            startActivity(intent)
        }

        btnPutBloodReq.setOnClickListener {
            val intent = Intent(this, RequestActivity::class.java)
            intent.putExtra("request", "blood")
            intent.putExtra("id", id.toString())
            startActivity(intent)
        }


    }
}