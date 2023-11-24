package com.nutrifacts.app.data.repository

import com.nutrifacts.app.data.local.room.HistoryDatabase
import com.nutrifacts.app.data.local.room.SavedProductsDatabase
import com.nutrifacts.app.data.retrofit.APIService

class ProductRepository private constructor(
    private val savedProductsDatabase: SavedProductsDatabase,
    private val historyDatabase: HistoryDatabase,
    private val apiService: APIService
) {
    //    fun getAllProducts():LiveData<PagingData<>>
    companion object {
        @Volatile
        private var instance: ProductRepository? = null
        fun getInstance(
            savedProductsDatabase: SavedProductsDatabase,
            historyDatabase: HistoryDatabase,
            apiService: APIService
        ) =
            instance ?: synchronized(this) {
                instance ?: ProductRepository(savedProductsDatabase, historyDatabase, apiService)
            }.also { instance = it }
    }
}