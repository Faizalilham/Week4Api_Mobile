package com.example.week4api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.week4api.Model.Barang
import com.example.week4api.Model.SingleResponse
import com.example.week4api.databinding.ActivityUpdateBinding
import com.example.week4api.webService.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        UpdateBarang()
        getData()
    }

    private fun getData(){
        binding.ETNama.setText(intent.getStringExtra("nama"))
        binding.ETKode.setText(intent.getStringExtra("kode"))
    }


    private fun UpdateBarang(){
        binding.BtnUpdate.setOnClickListener {
            val id = intent.getIntExtra("id",0)
            val nama = binding.ETNama.text.toString()
            val kode = binding.ETKode.text.toString().toInt()
            APIService.ApiEndPoint().UpdateBarang(id,nama,kode).enqueue(object :
                Callback<SingleResponse<Barang>> {
                override fun onResponse(
                    call: Call<SingleResponse<Barang>>,
                    response: Response<SingleResponse<Barang>>
                ) {
                    if (response.isSuccessful) {

                        Toast.makeText(
                            applicationContext,
                            "update data berhasil",
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(Intent(this@UpdateActivity,MainActivity::class.java).also{
                            finish()
                        })
                    }
                }

                override fun onFailure(call: Call<SingleResponse<Barang>>, t: Throwable) {
                    Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}