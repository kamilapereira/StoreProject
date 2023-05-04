package com.example.kameestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kameestore.listener.IProductLoadListener
import com.example.kameestore.models.ProductModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.ktx.Firebase

class ProductListActivity : AppCompatActivity(), IProductLoadListener {

    lateinit var productLoadListener: IProductLoadListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
        init()
        loadProductFromFirebase()
    }

    private fun loadProductFromFirebase() {
        val productModel : MutableList<ProductModel> = ArrayList()
        FirebaseCrashlytics.getInstance()
            .toString("id")

    }

    private fun init() {
        productLoadListener = this
    }

    override fun onProductLoadSuccess(productModeList: List<ProductModel>?) {
    }

    override fun onProductLoadFailed(message: String?) {
        Snackbar.make(,message,Snackbar.LENGTH_LONG).show()
    }
}