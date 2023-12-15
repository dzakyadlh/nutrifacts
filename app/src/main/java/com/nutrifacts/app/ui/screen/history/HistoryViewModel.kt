package com.nutrifacts.app.ui.screen.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutrifacts.app.data.Result
import com.nutrifacts.app.data.local.entity.History
import com.nutrifacts.app.data.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: HistoryRepository) : ViewModel() {
    private val _result: MutableStateFlow<Result<List<History>>> = MutableStateFlow(Result.Loading)
    val result: MutableStateFlow<Result<List<History>>> get() = _result

    fun insert(history: History) {
        viewModelScope.launch {
            repository.insert(history)
        }
    }

    fun delete(history: History) {
        viewModelScope.launch {
            repository.delete(history)
        }
    }

    fun getAllHistory(user_id: Int): Flow<List<History>> {
        return repository.getAllHistory(user_id)
    }
}