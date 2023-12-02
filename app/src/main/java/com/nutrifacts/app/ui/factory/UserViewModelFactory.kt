package com.nutrifacts.app.ui.factory

import androidx.lifecycle.ViewModelProvider
import com.nutrifacts.app.data.repository.UserRepository

class UserViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return when {
//            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
//                SignupViewModel(repository) as T
//            }
//
//            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
//        }
//    }
//
//    companion object {
//        @Volatile
//        private var INSTANCE: UserViewModelFactory? = null
//
//        @JvmStatic
//        fun getInstance(context: Context): UserViewModelFactory {
//            if (INSTANCE == null) {
//                synchronized(UserViewModelFactory::class.java) {
//                    INSTANCE = UserViewModelFactory(Injection.provideUserRepository(context))
//                }
//            }
//            return INSTANCE as UserViewModelFactory
//        }
//    }
}