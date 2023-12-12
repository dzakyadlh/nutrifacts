package com.nutrifacts.app.data.repository

import android.content.Context
import com.nutrifacts.app.data.local.entity.History
import com.nutrifacts.app.data.local.room.HistoryDao
import com.nutrifacts.app.data.local.room.HistoryDatabase
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class HistoryRepository(context: Context) {
    private val historyDao: HistoryDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = HistoryDatabase.getDatabase(context)
        historyDao = db.historyDao()
    }

    fun getAllHistory(user_id: Int): Flow<List<History>> {
        return historyDao.getAllHistory(user_id)
    }

    fun insert(history: History) {
        executorService.execute { historyDao.insert(history) }
    }

    fun delete(history: History) {
        executorService.execute { historyDao.delete(history) }
    }
}