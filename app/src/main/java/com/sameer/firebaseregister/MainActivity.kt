package com.sameer.firebaseregister

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password : EditText
    private lateinit var prograssBar : ProgressBar
    private lateinit var register : Button
    private lateinit var SignIn : TextView
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


          email = findViewById(R.id.id_email)
          password = findViewById(R.id.id_password)
          register = findViewById(R.id.id_register)
          SignIn =findViewById(R.id.id_signIn)
          prograssBar = findViewById(R.id.progressBar)

          register.setOnClickListener {
              SignUpHere()
          }

          SignIn.setOnClickListener {
              startActivity(LogInActivity.intent(this))
          }


    }

    private fun SignUpHere(){

        prograssBar.visibility = View.VISIBLE

        var mEmail : String = email.text.toString()
        var mPassword : String = password.text.toString()


        mAuth = FirebaseAuth.getInstance()

        Toast.makeText(applicationContext,mAuth.toString(),Toast.LENGTH_LONG).show()

        mAuth.createUserWithEmailAndPassword(mEmail,mPassword)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    startActivity(ProfileActivity.intent(this))
                    finish()
                    email.text.clear()
                    password.text.clear()

                }else{
                    Toast.makeText( applicationContext,"Fail", Toast.LENGTH_SHORT).show()
                }
            }
        prograssBar.visibility = View.GONE
    }
}