package com.nutrifacts.app.ui.screen.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutrifacts.app.data.use_case.ValidateEmail
import com.nutrifacts.app.data.use_case.ValidatePassword
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
//    private val repository: UserRepository,
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword:ValidatePassword = ValidatePassword()
):ViewModel() {
    var state by mutableStateOf(LoginFormState())

    private val validationEventChannel = Channel<LoginViewModel.ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }

            is LoginFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            is LoginFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailResult = validateEmail.validate(state.email)
        val passwordResult = validatePassword.validate(state.password)
        val hasError = listOf(
            emailResult,
            passwordResult,
        ).any { !it.success }

        state = state.copy(
            emailError = emailResult.errorMsg,
            passwordError = passwordResult.errorMsg,
        )
        if (hasError) return
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.success)
        }
    }

    sealed class ValidationEvent {
        object success : ValidationEvent()
    }
}