package com.sameer.firebaseregister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class MainActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password : EditText
    private lateinit var prograssBar : ProgressBar
    private lateinit var register : Button
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


          email = findViewById(R.id.id_email)
          password = findViewById(R.id.id_password)
          register = findViewById(R.id.id_register)
          prograssBar = findViewById(R.id.progressBar)

          register.setOnClickListener {
              SignUpHere()
          }


    }

    private fun SignUpHere(){

        prograssBar.visibility = View.VISIBLE

        val mEmail : String = email.text.toString()
        val mPassword : String = password.text.toString()


        mAuth = FirebaseAuth.getInstance()

        Toast.makeText(applicationContext,mAuth.toString(),Toast.LENGTH_LONG).show()

        mAuth.createUserWithEmailAndPassword(mEmail,mPassword)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(applicationContext,"Success",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText( applicationContext,"Fail", Toast.LENGTH_SHORT).show()
                }
            }
        prograssBar.visibility = View.GONE
    }
}