package com.example.android.teamandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.kycapp.R
import com.example.kycapp.ui.ProgressBar.ProgressBarFragment

class LoginActivity : AppCompatActivity() {
    private var btn_connexion: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_connexion = findViewById<View>(R.id.btn_connexion) as ImageView
        btn_connexion!!.setOnClickListener {
            val otherActivity = Intent(applicationContext, ProgressBarFragment::class.java)
            startActivity(otherActivity)
        }
    }
}