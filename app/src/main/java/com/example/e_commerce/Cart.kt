package com.example.e_commerce

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Cart : AppCompatActivity() {
    var cartProductList = Cartfun.getList()
    fun onUpdate(){
        var orderValueSum = Cartfun.calculateTotal()
        var orderValue = findViewById<TextView>(R.id.orderValueInt);
        var totalValue = findViewById<TextView>(R.id.totalValueInt);
        orderValue.text = orderValueSum.toString()
        totalValue.text = orderValueSum.toString()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

            var arrowBack = findViewById<ImageView>(R.id.arrow)

            var productListView = findViewById<RecyclerView>(R.id.recycleView)

           productListView.layoutManager = GridLayoutManager(this,1)
           productListView.adapter = choosenProductAdapter(this, cartProductList)
           onUpdate();

        arrowBack.setOnClickListener {
            this.onBackPressed()
            finish();
        }
    }

    override fun onBackPressed() {
        var cartIdList= ArrayList<Int>();
        for(item in cartProductList) {
            cartIdList.add(item.product.id)
        }
        val  intent = Intent()
        intent.putExtra("idList", cartIdList)
        setResult(2,intent)
        super.onBackPressed()
    }
}

  class choosenProductAdapter(var context: Context, var productList: ArrayList<CartItemSample>) : RecyclerView.Adapter<choosenProductAdapter.ProductViewHolder>(){

    class ProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var productName = itemView.findViewById<TextView>(R.id.prodname)
        var productImg = itemView.findViewById<ImageView>(R.id.prodimg)
        var categoryTextView = itemView.findViewById<TextView>(R.id.prodcategory)
        var priceTextView = itemView.findViewById<TextView>(R.id.prodprice)
        var deletebtnCart = itemView.findViewById<TextView>(R.id.deleteCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder{
        val view = LayoutInflater.from(context). inflate(
            R.layout.activity_cart_card, parent,
            false
        )
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        var cartModel =  productList.get(position)
        val productModel = cartModel.product
        holder.productName.text = productModel.name
        holder.productImg.setImageResource(productModel.link)
        holder.categoryTextView.text = productModel.category
        holder.priceTextView.text = "${productModel.price}"

        holder.deletebtnCart.setOnClickListener{
            val idx = Cartfun.deleteItem(productModel)
            this.notifyItemRemoved(idx)
            (context as Cart).onUpdate()
        }
    }
}
