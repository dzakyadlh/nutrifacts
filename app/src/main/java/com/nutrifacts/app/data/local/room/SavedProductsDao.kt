package com.nutrifacts.app.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nutrifacts.app.data.local.entity.SavedProducts
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedProductsDao {
    @Insert
    fun insert(savedProducts: SavedProducts)

    @Delete
    fun delete(savedProducts: SavedProducts)

    @Query("SELECT * from savedproducts ORDER BY dateAdded")
    fun getAllSavedProducts(): Flow<List<SavedProducts>>

    @Query("SELECT * from savedproducts WHERE barcode = :barcode")
    fun getSavedProduct(barcode: String): LiveData<SavedProducts>
}