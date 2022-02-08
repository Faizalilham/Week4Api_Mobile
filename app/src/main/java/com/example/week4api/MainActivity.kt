package com.example.week4api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week4api.Model.Barang
import com.example.week4api.Model.ListResponse
import com.example.week4api.adapter.MainAdapter
import com.example.week4api.databinding.ActivityMainBinding
import com.example.week4api.webService.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapater: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()

    }

    private fun setRecycler(barang: List<Barang>){
        mainAdapater = MainAdapter(barang, object : MainAdapter.AdapterClick{
            override fun onRead(barang: Barang) {
                startActivity(Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra("id", barang.id)
                })
            }

        })
        binding.Recycler.apply {
            adapter = mainAdapater
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun getData(){
        APIService.ApiEndPoint().getData().enqueue(object : Callback<ListResponse<Barang>> {
            override fun onResponse(call: Call<ListResponse<Barang>>, response: Response<ListResponse<Barang>>) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        setRecycler(body.data)
                    }
                }
            }

            override fun onFailure(call: Call<ListResponse<Barang>>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

}