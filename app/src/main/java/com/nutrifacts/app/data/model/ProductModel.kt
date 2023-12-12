package com.nutrifacts.app.data.model

data class ProductModel(
    var photoUrl: String = "",
    var nutritionData: String = "",
    var name: String,
    var company: String,
    var id: Int,
    var barcode: String,
    var nutritionLevel: String = ""
)
