package com.example.kameestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kameestore.adapter.MyProductAdapter
import com.example.kameestore.listener.IProductLoadListener
import com.example.kameestore.models.ProductModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
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
        val productModels : MutableList<ProductModel> = ArrayList()
        FirebaseDatabase.getInstance()
            .getReference("Product")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists())
                    {
                        for (productSnapshot in snapshot.children)
                        {
                            val productModel = productSnapshot.getValue(ProductModel::class.java)
                            productModel!!.key = productSnapshot.key
                            productModels.add(productModel)
                        }
                        productLoadListener.onProductLoadSuccess(productModels)
                }
                else
                productLoadListener.onProductLoadFailed("Product doesn't exist")
            }

                override fun onCancelled(error: DatabaseError) {
                    productLoadListener.onProductLoadFailed(error.message)
                }

            })

    }

    private fun init() {
        productLoadListener = this
    }


    override fun onProductLoadSuccess(productModeList: List<ProductModel>?) {
        val adapter = MyProductAdapter(this,productModeList)
        recycler_pList.adapter = adapter
    }

    override fun onProductLoadFailed(message: String?) {
        Snackbar.make(mainLayout,message!!,Snackbar.LENGTH_LONG).show()
    }
}