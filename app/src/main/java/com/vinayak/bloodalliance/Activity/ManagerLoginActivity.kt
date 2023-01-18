package com.vinayak.bloodalliance.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.vinayak.bloodalliance.R

class ManagerLoginActivity : AppCompatActivity() {

    private lateinit var etusername: EditText
    private lateinit var etpassword: EditText
    private lateinit var btnlogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_login)


        val database = baseContext.openOrCreateDatabase("data1.db", Context.MODE_PRIVATE, null)
        etusername = findViewById(R.id.etusername)
        etpassword = findViewById(R.id.etpassword)
        btnlogin = findViewById(R.id.btnlogin)

        btnlogin.setOnClickListener {

            val query = database.rawQuery(
                "SELECT password, id, role FROM CREDENTIALS WHERE username = ?",
                arrayOf((etusername.text).toString()),
            )

            var check = false
            var id = 0

            query.use {
                if(it.moveToFirst()) {
                    with(it) {
                        val pass = getString(0)
                        id = getInt(1)
                        if (pass == etpassword.text.toString()) {
                            check = true
                        }
                    }
                } else {
                    Toast.makeText(this, "Invalid Username", Toast.LENGTH_SHORT).show()
                }
            }

            if (check) {
                val intent = Intent(this, ManagerDashboardActivity::class.java)
                intent.putExtra("id", id.toString())
                database.close()
                startActivity(intent)
            } else {
                Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show()
            }
//            database.close()

        }
    }
}