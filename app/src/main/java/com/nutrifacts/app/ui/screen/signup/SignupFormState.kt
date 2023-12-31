package com.nutrifacts.app.ui.screen.signup

data class SignupFormState(
    val email: String = "",
    val emailError: String? = null,
    val username: String = "",
    val usernameError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null,
    val acceptedTermsConditions: Boolean = false,
    val termsConditionsError: String? = null
)
