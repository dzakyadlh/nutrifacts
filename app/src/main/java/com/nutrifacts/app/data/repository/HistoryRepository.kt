package com.nutrifacts.app.data.repository

import android.content.Context
import com.nutrifacts.app.data.local.entity.History
import com.nutrifacts.app.data.local.room.HistoryDao
import com.nutrifacts.app.data.local.room.HistoryDatabase
import kotlinx.coroutines.flow.Flow

class HistoryRepository(private val historyDao: HistoryDao) {

    companion object {

        @Volatile
        private var instance: HistoryRepository? = null

        fun getInstance(context: Context): HistoryRepository {
            return instance ?: synchronized(this) {
                if (instance == null) {
                    val database = HistoryDatabase.getDatabase(context)
                    instance = HistoryRepository(database.historyDao())
                }
                return instance as HistoryRepository
            }

        }
    }

    fun getAllHistory(user_id: Int): Flow<List<History>> {
        return historyDao.getAllHistory(user_id)
    }

    suspend fun insert(history: History) {
        historyDao.insert(history)
    }

    suspend fun delete(history: History) {
        historyDao.delete(history)
    }
}