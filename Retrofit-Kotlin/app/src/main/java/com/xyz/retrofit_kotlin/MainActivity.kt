package com.xyz.retrofit_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callApi()
    }

    private fun callApi() {
        val apiClient = Network.getInstance().create(ApiClient::class.java)
        val call = apiClient.getPosts()
        call.enqueue(object : Callback<List<ResponseModel>> {
            override fun onResponse(
                call: Call<List<ResponseModel>>,
                response: Response<List<ResponseModel>>
            ) {
                if (response.body() != null) {
                    response.body()?.let {
                        buildData(it)
                    }

                }
            }

            override fun onFailure(call: Call<List<ResponseModel>>, t: Throwable) {
            }

        })
    }

    private fun buildData(list: List<ResponseModel>) {
        val layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerview.layoutManager = layoutManager
        val postAdapter = PostAdapter(list)
        recyclerview.adapter = postAdapter
    }
}