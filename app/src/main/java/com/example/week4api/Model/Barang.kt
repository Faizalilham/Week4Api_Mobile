package com.example.week4api.Model

import com.google.gson.annotations.SerializedName

data class Barang (
        val id : Int,
        val nama : String,
        val kode : Int,
        val createdAt : String,
        val updatedAt : String
    )

data class ListResponse<T>(
    val msg : String,
    val status : Int,
    val data : MutableList<T>
)

data class SingleResponse<T>(
    val msg : String,
    val status : Int,
    val data : T
)
