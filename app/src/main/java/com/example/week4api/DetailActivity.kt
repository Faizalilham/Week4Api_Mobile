package com.example.week4api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.week4api.Model.Barang
import com.example.week4api.Model.ListResponse
import com.example.week4api.databinding.ActivityDetailBinding
import com.example.week4api.webService.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
        DeleteData()
    }

    private fun setData(barang: List<Barang>){
        binding.tvNama.text = barang[0].nama
        binding.tvKode.text = barang[0].kode.toString()
    }

    private fun getData(){
        APIService.ApiEndPoint().getDataById(intent.getIntExtra("id",0)).enqueue(object : Callback<ListResponse<Barang>> {
            override fun onResponse(call: Call<ListResponse<Barang>>, response: Response<ListResponse<Barang>>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        setData(body.data)
                    }
                }
            }

            override fun onFailure(call: Call<ListResponse<Barang>>, t: Throwable) {
                Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun DeleteData(){
        binding.BtnDelete.setOnClickListener {
            APIService.ApiEndPoint().DeleteBarang(intent.getIntExtra("id",0))
                .enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            Toast.makeText(
                                applicationContext,
                                "Data berhasil terhapus",
                                Toast.LENGTH_SHORT
                            ).show()
                            startActivity(
                                Intent(
                                    this@DetailActivity,
                                    MainActivity::class.java
                                ).also {
                                    finish()
                                })

                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
                    }

                })
        }
    }

}