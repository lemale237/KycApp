package com.example.kycapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.kycapp.MainActivity
import com.example.kycapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {


    private var btn_connexion: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_connexion = findViewById<View>(R.id.btn_connexion) as ImageView
        btn_connexion!!.setOnClickListener {

            var progressBar = findViewById<View>(R.id.progress_Bar) as ProgressBar
            progressBar.isVisible = true

            Handler().postDelayed(
                {
                    progressBar.isVisible = false
                    val otherActivity = Intent(applicationContext, MainActivity::class.java)
                    startActivity(otherActivity)
                    finish()
                }, 3000)
        }




        }



    }
