package com.example.e_commerce

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var addedEmail = findViewById<EditText>(R.id.emailTextBox).text
        var addedPassword = findViewById<EditText>(R.id.passwordTextBox).text
        var loginBtn = findViewById<TextView>(R.id.loginBtn)
        var registerBtn = findViewById<TextView>(R.id.registerText)


        var sharedpreferences: SharedPreferences = getSharedPreferences("Register", Context.MODE_PRIVATE);

        registerBtn.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }



        loginBtn.setOnClickListener {
            val email = sharedpreferences.getString("emailKey", "")
            val password = sharedpreferences.getString("passwordKey", "")
            if (addedEmail != null
                &&
                addedPassword != null
                &&
                addedEmail.toString() == email
                &&
                addedPassword.toString() == password
            ) {

                val intent = Intent(this, ProductList::class.java)
                startActivity(intent)
            }
            else if(email == "") {
                val toastMessage = "UserId not Registered."
                Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show();
            }
            else{
                val toastMessage = "Wrong userId or Password"
                Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show();
            }
        }

    }
}