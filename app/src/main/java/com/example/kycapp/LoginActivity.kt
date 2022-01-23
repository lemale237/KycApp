package com.example.kycapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.kycapp.MainActivity
import com.example.kycapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Login activity
 *
 * @constructor Create empty Login activity
 */
class LoginActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    // [START declare_auth]
    private lateinit var auth: FirebaseAuth
    // [END declare_auth]
    lateinit var progressBar:ProgressBar
    private var btn_connexion: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        variableIniotializations()
        onClickListeners()

    }

    /**
     * Variable iniotializations
     *
     */
    private fun variableIniotializations() {
        auth = Firebase.auth
        progressBar = findViewById<View>(R.id.progress_Bar) as ProgressBar
        btn_connexion = findViewById<View>(R.id.btn_connexion) as ImageView
    }

    private fun onClickListeners() {
        btn_connexion!!.setOnClickListener {
            SigninAction()

        }

        sign_up_btn.setOnClickListener {
            signupAction()
        }
    }

    /**
     * Signin action
     *
     */
    private fun SigninAction() {
        var password = login_password.text.toString()
        var login = login_email.text.toString()
        if (password.isNotEmpty() and login.isNotEmpty()) {
            progressBar.isVisible = true
            signIn(login, password)
        } else {
            Toast.makeText(
                this,
                "are you foul ? put the email and the password there !!!",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun signupAction() {
        Toast.makeText(
            this,
            resources.getText(R.string.feature_to_be_implemented),
            Toast.LENGTH_LONG
        ).show()
    }


    // [START on_start_check_user]
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload(currentUser);
        }
    }

    private fun reload(currentUser: FirebaseUser) {
        Log.e("slkdskd",currentUser.toString())
    }

    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed."+"${task.exception?.message}",
                        Toast.LENGTH_LONG).show()
                }
            }
        // [END sign_in_with_email]
    }

    private fun updateUI(user: FirebaseUser?) {
        progressBar.isVisible = false
        val otherActivity = Intent(applicationContext, MainActivity::class.java)
        startActivity(otherActivity)
        finish()
    }

    private fun signOut() {
        // [START auth_sign_out]
        Firebase.auth.signOut()
        // [END auth_sign_out]
    }

    }
