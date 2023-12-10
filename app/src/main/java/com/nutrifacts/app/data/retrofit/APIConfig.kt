package com.nutrifacts.app.data.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIConfig {
    fun getApiService(token: String): APIService {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val authInterceptor = Interceptor { chain ->
            val req = chain.request()
            val requestHeaders =
                req.newBuilder().addHeader("Authorization", "Bearer $token").build()
            chain.proceed(requestHeaders)
        }
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor).build()
        val retrofit =
            Retrofit.Builder().baseUrl("https://nutrifactsapp.et.r.appspot.com/").addConverterFactory(GsonConverterFactory.create())
                .client(client).build()
        return retrofit.create(APIService::class.java)
    }
}