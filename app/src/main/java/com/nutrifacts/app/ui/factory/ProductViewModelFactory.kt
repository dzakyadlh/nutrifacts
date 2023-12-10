package com.nutrifacts.app.ui.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nutrifacts.app.data.repository.ProductRepository
import com.nutrifacts.app.di.Injection
import com.nutrifacts.app.ui.screen.search.SearchViewModel

class ProductViewModelFactory(private val repository: ProductRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ProductViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: ProductViewModelFactory(Injection.provideProductRepository(context))
        }.also { instance = it }
    }
}