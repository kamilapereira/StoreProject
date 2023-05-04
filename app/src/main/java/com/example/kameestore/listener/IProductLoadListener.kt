package com.example.kameestore.listener

import com.example.kameestore.models.ProductModel

interface IProductLoadListener {
    fun onProductLoadSuccess(productModeList: List<ProductModel>?)
    fun onProductLoadFailed(message:String?)
}