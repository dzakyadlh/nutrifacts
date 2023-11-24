package com.nutrifacts.app.ui.screen.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.nutrifacts.app.data.repository.ProductRepository

class SearchViewModel(private val repository: ProductRepository) : ViewModel() {
    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

//    private val _result: MutableStateFlow<Result<List<Product>>> = MutableStateFlow(Result.Loading)
//    val result: StateFlow<Result<List<Product>>> get() = _result
//
//    fun getProducts() {
//        viewModelScope.launch {
//            repository.getAllProducts()
//                .catch { _result.value = Result.Error(it.message.toString()) }
//                .collect { product -> _result.value = Result.Success(result) }
//        }
//    }

//    fun searchProducts(newQuery:String){
//        _query.value = newQuery
//        viewModelScope.launch {
//            val filteredProducts = repository.searchProducts(newQuery)
//            _result.value = Result.Success(filteredProducts)
//        }
//    }
}