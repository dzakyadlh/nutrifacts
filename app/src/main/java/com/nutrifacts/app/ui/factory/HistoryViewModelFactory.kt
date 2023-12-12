package com.nutrifacts.app.ui.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nutrifacts.app.ui.screen.history.HistoryViewModel
import java.lang.ref.WeakReference

class HistoryViewModelFactory private constructor(private val context: Context) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                HistoryViewModel(context) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        private var INSTANCE: WeakReference<HistoryViewModelFactory>? = null

        @JvmStatic
        fun getInstance(context: Context): HistoryViewModelFactory {
            if (INSTANCE == null || INSTANCE?.get() == null) {
                synchronized(HistoryViewModelFactory::class.java) {
                    INSTANCE = WeakReference(HistoryViewModelFactory(context))
                }
            }
            return INSTANCE!!.get()!!
        }
    }
}