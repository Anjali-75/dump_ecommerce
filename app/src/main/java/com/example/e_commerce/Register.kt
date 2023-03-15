package com.example.e_commerce

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text


class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var name =  findViewById<EditText>(R.id.nameTextBox).text
        var password = findViewById<EditText>(R.id.passwordTextBox).text
        var email = findViewById<EditText>(R.id.emailTextBox).text
        var confirmPassword = findViewById<EditText>(R.id.confirmpasswordTextBox).text

        var sharedpreferences:SharedPreferences  = getSharedPreferences("Register", Context.MODE_PRIVATE);

        var registerbtn = findViewById<TextView>(R.id.Registerbtn)
        var loginbtn =  findViewById<TextView>(R.id.loginpage)

        registerbtn.setOnClickListener{
            if(confirmPassword.toString() != password.toString()){
                val toastMessage = "Password not Same !"
                Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show();
            }
            else if(name.toString() != "" && password.toString() != "" && email.toString() != "" ) {
            val editor = sharedpreferences.edit()

            editor.putString("nameKey",name.toString())
            editor.putString("passwordKey",password.toString())
            editor.putString("emailKey",email.toString())
            editor.commit()

                val intent = Intent(this, Login::class.java)
                startActivity(intent)
            }
          else{
                val toastMessage = "Please add all the fields"
                Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show();
            }
        }

        loginbtn.setOnClickListener{
            val intent =  Intent(this, Login::class.java)
            startActivity(intent)
        }

    }
}