package com.example.week4api.webService

import com.example.week4api.Model.Barang
import com.example.week4api.Model.ListResponse
import com.example.week4api.Model.SingleResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiEndPoint {

    @GET("barang")
    fun getData():Call<ListResponse<Barang>>

    @GET("barang/{id}")
    fun getDataById(@Path("id")id : Int):Call<ListResponse<Barang>>

    @FormUrlEncoded
    @POST("barang")
    fun PostData(@Field("nama")nama : String,
                 @Field("kode")kode : Int):Call<SingleResponse<Barang>>


}