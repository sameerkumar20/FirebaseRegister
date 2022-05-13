package com.sameer.firebaseregister

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogInActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var prograssBar: ProgressBar
    private lateinit var register: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );


        email = findViewById(R.id.id_email)
        password = findViewById(R.id.id_password)
        register = findViewById(R.id.id_register)
        prograssBar = findViewById(R.id.progressBar)

        register.setOnClickListener {
            SignUpHere()
        }
    }

    private fun SignUpHere() {

        prograssBar.visibility = View.VISIBLE

        val mEmail: String = email.text.toString()
        val mPassword: String = password.text.toString()


        mAuth = FirebaseAuth.getInstance()

        Toast.makeText(applicationContext, mAuth.toString(), Toast.LENGTH_LONG).show()

        mAuth.signInWithEmailAndPassword(mEmail, mPassword)
            .addOnCompleteListener {
                if (it.isSuccessful) {

                   // email.text.clear()
                   // password.text.clear()
                    startActivity(ProfileActivity.intent(this))
                  //  finish()
                } else {
                    Toast.makeText(applicationContext, "Fail", Toast.LENGTH_SHORT).show()
                }
            }
        prograssBar.visibility = View.GONE
    }

    companion object{
        fun intent(context : Context ): Intent {
            return Intent(context, LogInActivity::class.java)
        }
    }
}