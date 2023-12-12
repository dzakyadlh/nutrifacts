package com.nutrifacts.app.data.response

import com.google.gson.annotations.SerializedName

data class GetProductByBarcodeResponse(

	@field:SerializedName("product")
	val product: Product? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Product(

	@field:SerializedName("photoUrl")
	val photoUrl: String? = null,

	@field:SerializedName("nutrition_data")
	val nutritionData: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("company")
	val company: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("barcode")
	val barcode: String? = null,

	@field:SerializedName("nutrition_level")
	val nutritionLevel: String? = null
)
