package com.example.e_commerce

class Cartfun private  constructor(private val name: String){


    companion object {
        @Volatile
        private lateinit var instance: Cartfun
        private var cartItems = ArrayList<CartItemSample>()
        fun getInstance(name: String): Cartfun {
            synchronized(this) {
                if (!Companion::instance.isInitialized) {
                    instance = Cartfun(name)
                }
                return instance
            }
        }

        fun getList():ArrayList<CartItemSample>{
            return cartItems
        }

        fun addItem(product: productSample){
            val id = product.id
            var flag = false;
            for(products in cartItems){
                if(id == products.product.id){
                    products.quantity = products.quantity.plus(1)
                    flag= true
                    break
                }
            }
            if (flag) return
            cartItems.add(CartItemSample(product,1))
        }

        fun deleteItem(product: productSample) :Int{

            val id = product.id;
            var idx = -1;
            var flag = false
            for(products in cartItems){
                ++idx
                if(id == products.product.id){
                    flag = true;
                    break
                }
            }
            if(flag){
                cartItems.removeAt(idx);
            }
            return idx;
        }

        fun calculateTotal() :Int{
            var ordervalue = 0;
            for(products in cartItems){
                ordervalue += products.product.price
            }

            return ordervalue;
        }

    }

}