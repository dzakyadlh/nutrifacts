package com.nutrifacts.app.data.pref

data class UserModel(
    var id: Int,
    var token: String,
    var isLogin: Boolean = false
)
