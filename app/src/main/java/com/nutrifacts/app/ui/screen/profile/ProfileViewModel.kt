package com.nutrifacts.app.ui.screen.profile

import androidx.lifecycle.ViewModel
import com.nutrifacts.app.data.repository.UserRepository

class ProfileViewModel(private val repository: UserRepository) : ViewModel() {
    suspend fun logout() {
        repository.logout()
    }
}