package com.maneesh.reqresandroid.network

import com.example.reqres.network.models.BaseClass
import com.example.reqres.network.models.UserClass
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("/api/users")
   suspend fun getAllUsers(): BaseClass

    @GET("/api/users/{id}")
   suspend fun getUser(@Path(value="id") id:String): UserClass

    companion object{
//        Returning retrofit apiservice from companion object.
        private var apiService: APIService? = null
        fun getInstance() : APIService {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://reqres.in")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(APIService::class.java)
            }
            return apiService!!
        }
    }
}