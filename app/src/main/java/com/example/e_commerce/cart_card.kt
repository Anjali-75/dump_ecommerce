package com.example.e_commerce


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import android.view.View;
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class cart_card : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_card)

        val spinner = findViewById<Spinner>(R.id.dropdown_menu)
        val itemNumber = arrayOf<String?>("1","2","3","4","5")
        val arrayAdapter: ArrayAdapter<Any?> = ArrayAdapter<Any?>(this, R.layout.activity_spinner_list, itemNumber)
        arrayAdapter.setDropDownViewResource(R.layout.activity_spinner_list)
        spinner.adapter = arrayAdapter


    }
}

