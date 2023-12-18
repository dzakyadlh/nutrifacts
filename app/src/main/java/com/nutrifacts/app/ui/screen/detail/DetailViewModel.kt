package com.nutrifacts.app.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutrifacts.app.data.Result
import com.nutrifacts.app.data.local.entity.History
import com.nutrifacts.app.data.repository.ProductRepository
import com.nutrifacts.app.data.response.DeleteSavedProductResponse
import com.nutrifacts.app.data.response.Product
import com.nutrifacts.app.data.response.SaveProductResponse
import com.nutrifacts.app.data.response.SavedProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _result: MutableStateFlow<Result<Product>> =
        MutableStateFlow(Result.Loading)
    val result: StateFlow<Result<Product>> get() = _result

    private val _saved: MutableStateFlow<Result<List<SavedProduct>>> =
        MutableStateFlow(Result.Loading)
    val saved: StateFlow<Result<List<SavedProduct>>> get() = _saved

    fun getProductByBarcode(barcode: String) {
        viewModelScope.launch {
            repository.getProductByBarcode(barcode).collect { result ->
                _result.value = result
            }
        }
    }

    fun insertHistory(history: History) {
        viewModelScope.launch {
            repository.insertHistory(history)
        }
    }

    fun getSavedProduct(user_id: Int) {
        viewModelScope.launch {
            repository.getSavedProduct(user_id).collect { result ->
                _saved.value = result
            }
        }
    }

    fun saveProduct(
        name: String,
        company: String,
        photoUrl: String,
        barcode: String,
        user_id: Int
    ): Flow<Result<SaveProductResponse>> =
        repository.saveProduct(name, company, photoUrl, barcode, user_id)

    fun deleteSavedProduct(id: Int): Flow<Result<DeleteSavedProductResponse>> =
        repository.deleteProduct(id)
}