package com.example.firebase3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var Edt_email: EditText
    lateinit var Edt_password: EditText
    lateinit var Edt_conpassword: EditText
    lateinit var Btn_signup: Button
    lateinit var Edt_tvRedirect: TextView
    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Edt_email=findViewById(R.id.Edt_email)
        Edt_password=findViewById(R.id.Edt_password)
        Edt_conpassword=findViewById(R.id.Edt_conpassword)
        Btn_signup=findViewById(R.id.Btn_signup)
        Edt_tvRedirect=findViewById(R.id.Edt_tvRedirectLogin)
        auth=Firebase.auth

        Edt_tvRedirect.setOnClickListener {
            val intent= Intent(this, MainActivity:: class.java)
            startActivity(intent)
        }
        Btn_signup.setOnClickListener {
            signupuser()

        }
        private fun signupuser(){
            val email=Edt_email.text.toString()
            val password=Edt_password.text.toString()
            val conpassword=Edt_conpassword.text.toString()

            if (email.isBlank() ||password.isBlank() ||conpassword.isBlank()){
                Toast.makeText(this,"Please email and password cannot be blank", Toast.LENGTH_LONG).show()
                return
            }else if (password!=conpassword){
                Toast.makeText(this,"Password do not match", Toast.LENGTH_LONG).show()
                return
            }
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this,"Signed Successfully",Toast.LENGTH_LONG).show()
                    finish()
                }else{
                    Toast.makeText(this,"Failed to create",Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}

