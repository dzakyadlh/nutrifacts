package com.nutrifacts.app.data.model

data class TestUser (
    val id: String,
    val email: String,
    val username: String,
    val password: String,
    val photoUrl: String? = null,
)