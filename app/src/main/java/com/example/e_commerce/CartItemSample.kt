package com.example.e_commerce

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartItemSample(val product: productSample, var quantity: Int): Parcelable
