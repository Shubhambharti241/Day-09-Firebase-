package com.example.t11firebase

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val signButton = findViewById<Button>(R.id.buttonSignUp)
        val eTName = findViewById<TextInputEditText>(R.id.etName)
        val etMail = findViewById<TextInputEditText>(R.id.etMail)
        val userId  = findViewById<TextInputEditText>(R.id.etUsername)
        val userPassword = findViewById<TextInputEditText>(R.id.etPassword)

        signButton.setOnClickListener {

            val name = eTName.text.toString()
            val mail = etMail.text.toString()
            val uniqueId = userId.text.toString()
            val password = userPassword.text.toString()

            val user = User(name , mail , uniqueId,password)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(uniqueId).setValue(user).addOnSuccessListener {
                Toast.makeText(this,"User Registered",Toast.LENGTH_SHORT).show()
                etMail.text?.clear()
                eTName.text?.clear()
                userId.text?.clear()
                userPassword.text?.clear()
            }.addOnFailureListener {
                Toast.makeText(this, " Failed",Toast.LENGTH_SHORT).show()
            }

        }


    }

}