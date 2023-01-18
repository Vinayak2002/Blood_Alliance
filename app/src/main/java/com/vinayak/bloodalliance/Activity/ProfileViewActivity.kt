package com.vinayak.bloodalliance.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.vinayak.bloodalliance.Activity.LoginActivity
import com.vinayak.bloodalliance.R

fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
fun Int.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this.toString())

class ProfileViewActivity : AppCompatActivity() {

    private lateinit var txtName:TextView
    private lateinit var txtBldGrp:TextView
    private lateinit var txtLD:TextView
    private lateinit var txtND:TextView
    private lateinit var txtNDInf:TextView
    private lateinit var txtDonations:TextView
    private lateinit var txtCity:TextView
    private lateinit var txtState:TextView
    private lateinit var btnUpdate:Button

    private lateinit var etName:EditText
    private lateinit var etBldGrp:EditText
    private lateinit var etLD:EditText
    private lateinit var etDonations:EditText
    private lateinit var etCity:EditText
    private lateinit var etState:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_view)

        txtName = findViewById(R.id.txtName)
        txtBldGrp = findViewById(R.id.txtBldGrp)
        txtLD = findViewById(R.id.txtLD)
        txtND = findViewById(R.id.txtND)
        txtNDInf = findViewById(R.id.txtNDInf)
        txtDonations = findViewById(R.id.txtDonations)
        txtCity = findViewById(R.id.txtCity)
        txtState = findViewById(R.id.txtState)
        btnUpdate = findViewById(R.id.btnUpdate)

        etName = findViewById(R.id.etName)
        etBldGrp = findViewById(R.id.etBldGrp)
        etLD = findViewById(R.id.etLD)
        etDonations = findViewById(R.id.etDonations)
        etCity = findViewById(R.id.etCity)
        etState = findViewById(R.id.etState)

        var id = 0

        if (intent != null) {
            id = intent.getStringExtra("id").toString().toInt()
        } else {
            finish()
            Toast.makeText(this, "Some unexpected error occurred!!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val database = baseContext.openOrCreateDatabase("data1.db", Context.MODE_PRIVATE, null)

        val query = database.rawQuery("SELECT * FROM USERS WHERE id = ?",
            arrayOf(id.toString())
        )

        query.use {
            if (it.moveToFirst()) {
                with(it) {
                    val name = getString(1)
                    val bldgrp = getString(2)
                    val ld = getString(3)
                    val nd = getString(4)
                    val donations = getInt(5)
                    val city = getString(6)
                    val state = getString(7)

                    etName.text = name.toEditable()
                    etBldGrp.text = bldgrp.toEditable()
                    etLD.text = ld.toEditable()
                    txtNDInf.text=  nd.toString()
                    etDonations.text = donations.toEditable()
                    etCity.text = city.toEditable()
                    etState.text = state.toEditable()
                }
            }
        }

        btnUpdate.setOnClickListener {
            val name = etName.text.toString()
            val bldgrp = etBldGrp.text.toString()
            val ld = etLD.text.toString()
            val donations = etDonations.text.toString()
            val city = etCity.text.toString()
            val state = etState.text.toString()

            val sql = database.rawQuery("UPDATE USERS SET name = ?, bldgrp = ?, last_donation = ?, next_donation = DATE($ld, '+3 months')," +
                    "donations = ?, city = ?, state = ? WHERE id = ?",
            arrayOf<String>(name, bldgrp, ld, donations, city, state, id.toString()))
            sql.moveToFirst()
            sql.close()
//            database.close()

            Toast.makeText(this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ProfileViewActivity::class.java)
            intent.putExtra("id", id.toString())
            startActivity(intent)
        }
    }
}