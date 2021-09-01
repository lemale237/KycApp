package com.example.kycapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.kycapp.ui.LoginActivity

class SplashScreenAcivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen_acivity)
        Handler().postDelayed({
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }, 5000)
    }
}