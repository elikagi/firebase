package com.example.firebase3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var Edt_EmailAddress:EditText
    lateinit var Edt_Password:EditText
    lateinit var btn_Login:Button
    lateinit var Edt_Redirectsignup:TextView

    // Creating firebaseAuth object
    lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceStatesavedInstanceState)
        setContentView(R.layout.activity_Login)

        // View Binding
        Edt_EmailAddress=findViewById(R.id.Edt_EmailAddress)
        Edt_Password = findViewById(R.id.Edt_Password)
        btn_Login = findViewById(R.id.btn_Login)
        Edt_Redirectsignup = findViewById(R.id.Edt_RedirectSignUp)

        // initialising Firebase auth object
        auth = FirebaseAuth.getInstance()

        btn_Login.setOnClickListener {
            login()
        }

        Edt_Redirectsignup.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            // using finish() to end the activity
            finish()
        }
    }

    private fun login() {
        val email = Edt_EmailAddress.text.toString()
        val pass = Edt_Password.text.toString()
        // calling signInWithEmailAndPassword(email, pass)
        // function using Firebase auth object
        // On successful response Display a Toast
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully LoggedIn", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(this, "Log In failed ", Toast.LENGTH_SHORT).show()
        }
    }
