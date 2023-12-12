package com.nutrifacts.app.ui.screen.history

import android.content.Context
import androidx.lifecycle.ViewModel
import com.nutrifacts.app.data.Result
import com.nutrifacts.app.data.local.entity.History
import com.nutrifacts.app.data.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class HistoryViewModel(context: Context) : ViewModel() {
    private val historyRepository: HistoryRepository = HistoryRepository(context)
    private val _result: MutableStateFlow<Result<List<History>>> = MutableStateFlow(Result.Loading)
    val result: MutableStateFlow<Result<List<History>>> get() = _result

    fun insert(history: History) {
        historyRepository.insert(history)
    }

    fun delete(history: History) {
        historyRepository.delete(history)
    }

    fun getAllHistory(user_id: Int): Flow<List<History>> {
        return historyRepository.getAllHistory(user_id)
    }
}