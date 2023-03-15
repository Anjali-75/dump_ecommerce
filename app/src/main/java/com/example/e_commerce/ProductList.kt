package com.example.e_commerce

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.parcelize.Parcelize


class ProductList : AppCompatActivity() {

    var allproductList = arrayListOf<productSample>();

    init {
        allproductList.add(productSample(1, "Prod A", R.drawable.pic1, "apparels", 100,false));
        allproductList.add(productSample(2, "Prod B", R.drawable.pic1, "apparels", 150,false));
        allproductList.add(productSample(3, "Prod C", R.drawable.pic1, "apparels", 105, false));
        allproductList.add(productSample(4, "Prod D", R.drawable.pic1, "apparels", 106, false));
        allproductList.add(productSample(5, "Prod E", R.drawable.pic1, "apparels", 102, false));
    }
    private lateinit var productAdapter : ProductAdapter


    var activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

        if (result.resultCode == 2 && result.data != null) {
            val resultIdList = result.data!!.getIntegerArrayListExtra("idList")
            val set = HashSet<Int>(resultIdList)
            if(set != null){
                for(item in allproductList){
                    item.isAdded = false
                    if(set.contains(item.id)){
                        item.isAdded = true;
                    }
                }
            }
            productAdapter.getList(allproductList);
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        var productListView = findViewById<RecyclerView>(R.id.product_list)

        productAdapter = ProductAdapter(this, allproductList, activityResultLauncher)
        productListView.layoutManager = GridLayoutManager(this, 2)
        productListView.adapter = productAdapter

        var cartbtn = findViewById<ImageView>(R.id.cart)

        cartbtn.setOnClickListener{
            val  intent = Intent( this, Cart::class.java)
            activityResultLauncher.launch(intent)
        }
    }
}

class ProductAdapter(var context: Context, var ProductList: ArrayList<productSample>, var activityResultLauncher: ActivityResultLauncher<Intent>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    class ProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var productNameTextView = itemView.findViewById<TextView>(R.id.prod_name)
        var productImg = itemView.findViewById<ImageView>(R.id.img)
        var categoryTextView = itemView.findViewById<TextView>(R.id.category)
        var priceTextView = itemView.findViewById<TextView>(R.id.price)
        var addToCartBtn = itemView.findViewById<TextView>(R.id.addToCartBtn)
    }

    fun getList(updatedList: ArrayList<productSample>){
       for(idx in ProductList.indices){
           ProductList[idx] = updatedList[idx]
       }
        this.notifyDataSetChanged();
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder{
        val view = LayoutInflater.from(context). inflate(
            R.layout.activity_card, parent,
            false
        )
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ProductList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        var productModel =  ProductList.get(position)
        holder.productNameTextView.text = productModel.name
        holder.productImg.setImageResource(productModel.link)
        holder.categoryTextView.text = productModel.category
        holder.priceTextView.text = "${productModel.price}"
        holder.itemView.setOnClickListener{
            val  intent = Intent(context, Product::class.java)
            intent.putExtra("name",productModel.name)
            intent.putExtra("img", productModel.link)
            intent.putExtra("productModel", productModel)
            intent.putExtra("price", "${productModel.price}")
            activityResultLauncher.launch(intent)
        }

        if(!productModel.isAdded){
            holder.addToCartBtn.text = "Add"
            holder.addToCartBtn.setOnClickListener{
                holder.addToCartBtn.text = "Go to Cart"
                Cartfun.addItem(productModel)
                productModel.isAdded= true;
                this.notifyItemChanged(position)
            }
        }
        else{
            holder.addToCartBtn.text = "Go To Cart"
            holder.addToCartBtn.setOnClickListener{
                    var new_intent = Intent(context,Cart::class.java )
                    activityResultLauncher.launch(new_intent)
            }
        }

    }
}

@Parcelize
data class productSample(val id: Int, val name: String, val link: Int, val category: String, val price: Int, var isAdded: Boolean): Parcelable