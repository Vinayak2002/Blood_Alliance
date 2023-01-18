package com.vinayak.bloodalliance.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.vinayak.bloodalliance.R


class SplashScreen_Main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        if (!prefs.getBoolean("firstTime", false)) {
            databaseSetup()

            val editor = prefs.edit()
            editor.putBoolean("firstTime", true)
            editor.apply()
        }

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // we used the postDelayed(Runnable, time) method
        // to send a message with a delayed time.
        Handler().postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000) // 3000 is the delayed time in milliseconds.
    }

    private fun databaseSetup() {
        val database = baseContext.openOrCreateDatabase("data1.db", MODE_PRIVATE, null)
        val query = database.rawQuery(
            "CREATE TABLE USERS(" +
                    " ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " NAME varchar(255) NOT NULL," +
                    " BLDGRP varchar(3) NOT NULL, " +
                    " LAST_DONATION DATE," +
                    " NEXT_DONATION DATE," +
                    " DONATIONS INTEGER NOT NULL, " +
                    " CITY varchar(255) NOT NULL, " +
                    " STATE varchar(255) NOT NULL )",
            arrayOf<String>()
        )
        query.moveToFirst()
        query.close()

        val query1 = database.rawQuery(
            "CREATE TABLE BLOOD_PACKET" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "BLDGRP varchar(3) NOT NULL, " +
                    "DONATED_ON DATE NOT NULL," +
                    "DONATED_BY DATE NOT NULL," +
                    "EXPIRY DATE NOT NULL, " +
                    "CITY varchar(255) NOT NULL, " +
                    "STATE varchar(255) NOT NULL, " +
                    "FOREIGN KEY(DONATED_BY) REFERENCES USERS(ID) )",
            arrayOf<String>()
        )
        query1.moveToFirst()
        query1.close()

        val query2 = database.rawQuery(
            "CREATE TABLE MANAGERS(" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "NAME varchar(255) NOT NULL," +
                    "CITY varchar(255) NOT NULL, " +
                    "STATE varchar(255) NOT NULL )",
            arrayOf<String>()
        )
        query2.moveToFirst()
        query2.close()

        val query3 = database.rawQuery(
            "CREATE TABLE BLOOD_REQ(" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "REQUEST_BY INTEGER NOT NULL," +
                    "REQUEST_DATE DATE NOT NULL," +
                    "CITY varchar(255) NOT NULL, " +
                    "STATE varchar(255) NOT NULL, " +
                    "STATUS CHAR(1) NOT NULL," +
                    "BLDGRP CHAR(3) NOT NULL," +
                    "FOREIGN KEY(REQUEST_BY) REFERENCES USERS(ID))",
            arrayOf<String>()
        )
        query3.moveToFirst()
        query3.close()

        val query4 = database.rawQuery(
            "CREATE TABLE DONATION_REQ(" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "REQUEST_BY INTEGER NOT NULL," +
                    "REQUEST_DATE DATE NOT NULL," +
                    "BLDGRP varchar(3) NOT NULL," +
                    "CITY varchar(255) NOT NULL, " +
                    "STATE varchar(255) NOT NULL, " +
                    "STATUS CHAR(1) NOT NULL," +
                    "FOREIGN KEY(REQUEST_BY) REFERENCES USERS(ID) )",
            arrayOf<String>()
        )
        query4.moveToFirst()
        query4.close()

        val query5 = database.rawQuery(
            "CREATE TABLE CREDENTIALS (" +
                    "USERNAME varchar(255) PRIMARY KEY, " +
                    "PASSWORD varchar(12) NOT NULL," +
                    "ID INTEGER NOT NULL," +
                    "ROLE varchar(10) NOT NULL )",
            arrayOf<String>()
        )
        query5.moveToFirst()
        query5.close()

        val sqlstatement = mutableListOf<String>()

        // USERS
        sqlstatement.add("INSERT INTO USERS VALUES (1001, 'VINAYAK SHRIVASTAVA', 'AB+', '2020-10-02', '2021-03-02', 1, 'RAIPUR', 'CHHATTISGARH')")
        sqlstatement.add("INSERT INTO USERS VALUES (1002, 'MOHD MERAJ', 'A+', '2020-10-02', '2021-03-02', 2, 'DELHI', 'DELHI')")
        sqlstatement.add("INSERT INTO USERS VALUES (1003, 'KISHAN GOPAL MISHRA', 'B+', '2020-10-02', '2021-03-02', 1, 'AMBIKAPUR', 'CHHATTISGARH')")
        sqlstatement.add("INSERT INTO USERS VALUES (1004, 'SUYASH SINHA', 'O+', '2020-10-02', '2021-03-02', 2, 'RAIPUR', 'CHHATTISGARH')")
        sqlstatement.add("INSERT INTO USERS VALUES (1005, 'AKSHIT RANA', 'B-', '2020-10-02', '2021-03-02', 3, 'AMBIKAPUR', 'CHHATTISGARH')")
        sqlstatement.add("INSERT INTO USERS VALUES (1006, 'BHAVYA ALOK', 'A-', '2020-10-02', '2021-03-02', 4, 'DELHI', 'DELHI')")
        sqlstatement.add("INSERT INTO USERS VALUES (1007, 'AMAN SINGH', 'O-', 'NULL', NULL, 0, 'DELHI', 'DELHI')")
        sqlstatement.add("INSERT INTO USERS VALUES (1008, 'AADITYA KUMAR', 'AB-', '2020-10-02', '2021-03-02', 3, 'RAIPUR', 'CHHATTISGARH')")
        sqlstatement.add("INSERT INTO USERS VALUES (1009, 'SANKALP VARMA', 'O+', '2020-10-02', '2021-03-02', 2, 'DELHI', 'DELHI')")

        // MANAGERS
        sqlstatement.add("INSERT INTO MANAGERS VALUES (2001, 'TUSHAR RUSIA', 'BHILAI', 'CHHATTISGARH')")
        sqlstatement.add("INSERT INTO MANAGERS VALUES (2002, 'AMAAN A KAAZI', 'DELHI', 'DELHI')")
        sqlstatement.add("INSERT INTO MANAGERS VALUES (2003, 'KEVIN HART', 'RAIPUR', 'CHHATTISGARH')")

        // DONATION_REQ
        sqlstatement.add("INSERT INTO DONATION_REQ VALUES (5001, 1008, '2021-02-10', 'O+', 'BHILAI', 'CHHATTISGARH', 'N')")
        sqlstatement.add("INSERT INTO DONATION_REQ VALUES (5002, 1009, '2021-03-20', 'A+', 'DELHI', 'DELHI', 'Y')")

        // CREDENTIALS
        sqlstatement.add("INSERT INTO CREDENTIALS VALUES ('vinayak2002', 'password', 1001, 'USER')")
        sqlstatement.add("INSERT INTO CREDENTIALS VALUES ('meraj2001', 'password', 1002, 'USER')")
        sqlstatement.add("INSERT INTO CREDENTIALS VALUES ('kishan2001', 'password', 1003, 'USER')")
        sqlstatement.add("INSERT INTO CREDENTIALS VALUES ('suyash2002', 'password', 1004, 'USER')")
        sqlstatement.add("INSERT INTO CREDENTIALS VALUES ('akshit2002', 'password', 1005, 'USER')")
        sqlstatement.add("INSERT INTO CREDENTIALS VALUES ('bhavya2002', 'password', 1006, 'USER')")
        sqlstatement.add("INSERT INTO CREDENTIALS VALUES ('tushar2002', 'password', 2001, 'MANAGER')")
        sqlstatement.add("INSERT INTO CREDENTIALS VALUES ('amaan2002', 'password', 2002, 'MANAGER')")
        sqlstatement.add("INSERT INTO CREDENTIALS VALUES ('kevin', 'password', 2003, 'MANAGER')")
        sqlstatement.add("INSERT INTO CREDENTIALS VALUES ('aman2002', 'password', 1007, 'USER')")
        sqlstatement.add("INSERT INTO CREDENTIALS VALUES ('aaditya2001', 'password', 1008, 'USER')")
        sqlstatement.add("INSERT INTO CREDENTIALS VALUES ('sankalp2002', 'password', 1009, 'USER')")

        // BLOOD_REQ
        sqlstatement.add("INSERT INTO BLOOD_REQ VALUES (4001, 1007, '2021-01-10', 'DELHI', 'DELHI', 'N', 'A-')")

        // BLOOD_PACKET
        sqlstatement.add("INSERT INTO BLOOD_PACKET VALUES (3001, 'AB+', '2020-10-02', 1001, '2030-10-02', 'RAIPUR', 'CHHATTISGARH')")
        sqlstatement.add("INSERT INTO BLOOD_PACKET VALUES (3002, 'A+', '2020-10-02', 1002, '2030-10-02', 'DELHI', 'DELHI')")
        sqlstatement.add("INSERT INTO BLOOD_PACKET VALUES (3003, 'B+', '2020-10-02', 1003, '2030-10-02', 'AMBIKAPUR', 'CHHATTISGARH')")
        sqlstatement.add("INSERT INTO BLOOD_PACKET VALUES (3004, 'O+', '2020-10-02', 1004, '2030-10-02', 'RAIPUR', 'CHHATTISGARH')")
        sqlstatement.add("INSERT INTO BLOOD_PACKET VALUES (3005, 'B-', '2020-10-02', 1005, '2030-10-02', 'AMBIKAPUR', 'CHHATTISGARH')")
        sqlstatement.add("INSERT INTO BLOOD_PACKET VALUES (3006, 'A-', '2020-10-02', 1006, '2030-10-02', 'DELHI', 'DELHI')")

        for (sql in sqlstatement) {
            val sqlQ = database.rawQuery(sql, arrayOf<String>())
            sqlQ.moveToFirst()
            sqlQ.close()
        }
        database.close()
    }


}