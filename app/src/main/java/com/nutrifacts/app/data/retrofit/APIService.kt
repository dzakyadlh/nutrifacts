package com.nutrifacts.app.data.retrofit

import com.nutrifacts.app.data.response.GetAllUserResponse
import com.nutrifacts.app.data.response.GetUserByIdResponse
import com.nutrifacts.app.data.response.LoginResponse
import com.nutrifacts.app.data.response.SignupResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIService {
    @FormUrlEncoded
    @POST("user/signup")
    suspend fun signup(
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): SignupResponse

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @GET("user")
    suspend fun getAllUser(
    ): GetAllUserResponse
    @FormUrlEncoded
    @GET("user/{id}")
    suspend fun getUserById(
        @Path("id") id: String
    ): GetUserByIdResponse

    //    @FormUrlEncoded
//    @PUT("user/{id}")
//    suspend fun editUser(
//        @Path("id") id: String
//    ): EditUserResponse

//    @FormUrlEncoded
//    @GET("product")
//    suspend fun getAllProducts(
//    ): GetAllProductsResponse

//    @FormUrlEncoded
//    @GET("user/{id}")
//    suspend fun getProductDetail(
//        @Path("id") id: String
//    ): GetProductDetailResponse
}