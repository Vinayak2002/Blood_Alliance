package com.vinayak.bloodalliance.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.vinayak.bloodalliance.R

class RegistrationActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etMobile: EditText
    private lateinit var etUsernameR: EditText
    private lateinit var etBloodGroup: EditText
    private lateinit var lastDOD: EditText
    private lateinit var numberOfDonations: EditText
    private lateinit var etCity: EditText
    private lateinit var etState: EditText
    private lateinit var etPasswordR: EditText
    private lateinit var btnRegister: Button
    private lateinit var txtAlready: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val database = baseContext.openOrCreateDatabase("data1.db", Context.MODE_PRIVATE, null)

        title = "Register"

        etName = findViewById(R.id.etName)
        etMobile = findViewById(R.id.etMobile)
        etUsernameR = findViewById(R.id.etUsernameR)
        etBloodGroup = findViewById(R.id.etBloodGroup)
        lastDOD = findViewById(R.id.lastDOD)
        numberOfDonations = findViewById(R.id.numberOfDonations)
        etCity = findViewById(R.id.etCity)
        etState = findViewById(R.id.etState)
        etPasswordR = findViewById(R.id.etPasswordR)
        btnRegister = findViewById(R.id.btnRegister)
        txtAlready = findViewById(R.id.txtAlready)




        btnRegister.setOnClickListener {

            val query = database.rawQuery("SELECT id from USERS",
                arrayOf())
            var id = 0
            query.use {
                if (it.moveToLast()) {
                    with(it) {
                        id = getInt(0)
                    }
                }
            }
            id++

            val sql = database.rawQuery("INSERT INTO USERS(id, name, bldgrp, last_donation, next_donation, donations, city, state) VALUES (?, ?, ?, ?, DATE(?, '+3 months'), ?, ?, ?)",
            arrayOf<String>(id.toString(), etName.text.toString(), etBloodGroup.text.toString(), lastDOD.text.toString(), lastDOD.text.toString(),
                numberOfDonations.text.toString(), etCity.text.toString(), etState.text.toString()))

            sql.moveToFirst()
            sql.close()

            val insert = database.rawQuery("INSERT INTO CREDENTIALS(username, password, id, role) VALUES(?, ?, ?, ?)",
            arrayOf<String>(etUsernameR.text.toString(), etPasswordR.text.toString(), id.toString(), "USER"))
            insert.moveToFirst()
            insert.close()
            database.close()


            Toast.makeText(this, "User Registered!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, LoginActivity::class.java)
            intent.data
            startActivity(intent)
        }

        txtAlready.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}