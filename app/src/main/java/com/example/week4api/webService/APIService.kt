package com.example.week4api.webService

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIService {
    companion object{
        private var retrofit: Retrofit? = null
        private var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

        private fun getClient(): Retrofit {
            return if (retrofit == null){
                retrofit = Retrofit.Builder().baseUrl(Constant.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                retrofit!!
            }else{
                retrofit!!
            }
        }
        fun ApiEndPoint() : ApiEndPoint = getClient().create(ApiEndPoint::class.java)
    }

}

class Constant{
    companion object{
        const val BASE_URL = "https://apibarang.herokuapp.com/"
    }
}
