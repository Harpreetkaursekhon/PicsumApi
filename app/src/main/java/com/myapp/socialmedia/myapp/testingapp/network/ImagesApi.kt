package com.myapp.socialmedia.myapp.testingapp.network

import com.myapp.socialmedia.myapp.testingapp.model.ImagesDataClass
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


const val BASE_URL= "https://picsum.photos/"
interface ImagesApi {

    @GET("v2/list")
    fun getImages(): Call<List<ImagesDataClass>>

    companion object {

        operator fun invoke(): ImagesApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ImagesApi::class.java)
        }
    }
}