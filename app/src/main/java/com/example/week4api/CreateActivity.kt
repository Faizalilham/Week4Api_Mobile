package com.example.week4api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.week4api.Model.Barang
import com.example.week4api.Model.SingleResponse
import com.example.week4api.databinding.ActivityCreateBinding
import com.example.week4api.webService.APIService
import com.example.week4api.webService.ApiEndPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createData()

    }

    private fun createData(){
        binding.btnCreate.setOnClickListener {
            val name = binding.etName.text.toString()
            val kode = binding.etCode.text.toString().toInt()
            APIService.ApiEndPoint().PostData(name, kode).enqueue(object :
                Callback<SingleResponse<Barang>> {
                override fun onResponse(
                    call: Call<SingleResponse<Barang>>,
                    response: Response<SingleResponse<Barang>>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) {
                            Toast.makeText(applicationContext, "Create data successfully", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@CreateActivity, MainActivity::class.java))
                            finish()
                        }
                    }
                }

                override fun onFailure(call: Call<SingleResponse<Barang>>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

}