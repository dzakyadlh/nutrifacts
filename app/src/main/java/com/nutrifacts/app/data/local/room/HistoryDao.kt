package com.nutrifacts.app.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nutrifacts.app.data.local.entity.History
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Insert
    fun insert(history: History)

    @Delete
    fun delete(history: History)

    @Query("SELECT * from history ORDER BY dateAdded")
    fun getAllSavedProducts(): Flow<List<History>>

    @Query("SELECT * from history WHERE barcode = :barcode")
    fun getSavedProduct(barcode: String): LiveData<History>
}