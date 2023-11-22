package com.nutrifacts.app.data.repository

import com.nutrifacts.app.data.pref.UserModel
import com.nutrifacts.app.data.pref.UserPreference
import com.nutrifacts.app.data.retrofit.APIService
import kotlinx.coroutines.flow.Flow

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: APIService
) {

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun logout() {
        userPreference.logout()
    }

//    fun signup(
//        username: String,
//        email: String,
//        password: String
//    ) = liveData {
//        emit(Result.Loading)
//        try {
//            val response = apiService.register(username, email, password)
//            emit(Result.Success(response))
//        } catch (e: HttpException) {
//            val errorBody = e.response()?.errorBody()?.string()
//            val response = Gson().fromJson(errorBody, ErrorResponse::class.java)
//            emit(Result.Error(response.message.toString()))
//        }
//    }
//
//    fun login(email: String, password: String) = liveData {
//        emit(Result.Loading)
//        try {
//            val response = apiService.login(email, password)
//            emit(Result.Success(response))
//        } catch (e: HttpException) {
//            val errorBody = e.response()?.errorBody()?.string()
//            val response = Gson().fromJson(errorBody, ErrorResponse::class.java)
//            emit(Result.Error(response.message.toString()))
//        }
//    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            apiService: APIService
        ): UserRepository = instance ?: synchronized(this) {
            instance ?: UserRepository(userPreference, apiService)
        }.also { instance = it }
    }

}