package com.nutrifacts.app.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.nutrifacts.app.data.local.room.HistoryDatabase
import com.nutrifacts.app.data.local.room.RemoteKeys
import com.nutrifacts.app.data.local.room.SavedProductsDatabase
import com.nutrifacts.app.data.response.GetAllProductResponseItem
import com.nutrifacts.app.data.retrofit.APIService

//@OptIn(ExperimentalPagingApi::class)
//class ProductRemoteMediator(
//    private val historyDatabase: HistoryDatabase,
//    private val savedProductsDatabase: SavedProductsDatabase,
//    private val apiService: APIService
//): RemoteMediator<Int, GetAllProductResponseItem>() {
//
//    companion object{
//        const val INITIAL_PAGE_INDEX = 1
//    }
//
//    override suspend fun initialize(): InitializeAction {
//        return InitializeAction.LAUNCH_INITIAL_REFRESH
//    }
//
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, GetAllProductResponseItem>
//    ): MediatorResult {
//        val page = when(loadType){
//            LoadType.REFRESH -> {
//                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
//                remoteKeys?.nextKey?.minus(1) ?: INITIAL_PAGE_INDEX
//            }
//        }
//    }
//
//    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, GetAllProductResponseItem>): RemoteKeys?{
//        return state.anchorPosition?.let { position->
//            state.closestItemToPosition(position)?.id?.let { id->
//                historyDatabase.remoteKeysDao().getRemoteKeysId(id)
//            }
//        }
//    }
//
//}