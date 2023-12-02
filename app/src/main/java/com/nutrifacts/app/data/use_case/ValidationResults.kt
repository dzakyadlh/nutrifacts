package com.nutrifacts.app.data.use_case

data class ValidationResults(
    val success: Boolean,
    val errorMsg: String? = null
)
