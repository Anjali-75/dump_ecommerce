package com.example.e_commerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView

class Product : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val imageView = findViewById<ImageView>(R.id.img_main)
        val nameTextView = findViewById<TextView>(R.id.name)
        val priceTextView = findViewById<TextView>(R.id.price)
        val smallImageView = findViewById<ImageView>(R.id.img_small)
        val addToCartBtnInside = findViewById<TextView>(R.id.addToCartBtnInside)

        imageView.setImageResource(intent.getIntExtra("img", 0))
        nameTextView.text = intent.getStringExtra("name")
        priceTextView.text = intent.getStringExtra("price").toString()
        smallImageView.setImageResource(intent.getIntExtra("img", 0))

        var cartbtn = findViewById<ImageView>(R.id.cart)

        cartbtn.setOnClickListener {
            val intent = Intent(this, Cart::class.java)
            this.startActivity(intent)
        }

        val arrow = findViewById<ImageView>(R.id.arrow)
        arrow.setOnClickListener {
            finish()
        }

        addToCartBtnInside.setOnClickListener {
            intent.getParcelableExtra<productSample>("productModel")
                ?.let { it1 -> Cartfun.addItem(it1) }
            addToCartBtnInside.text = "Go To Cart"
            addToCartBtnInside.setOnClickListener {
                var new_intent = Intent(this, Cart::class.java)
                this.startActivity(new_intent)
            }


        }
    }
}