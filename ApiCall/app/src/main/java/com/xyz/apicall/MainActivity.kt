package com.xyz.apicall

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnHitApi.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnHitApi -> {
                callApi()
            }
        }
    }

    private fun callApi() {

        val apiClient = Network.getInstance().create(ApiClient::class.java)

        if (isDataValid()) {
            val getUser = apiClient.getUser(etEnterId.text.toString().toInt())

            getUser.enqueue(object : Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    response.body()?.let {
                        tvCompany.text = it.ad?.company
                        tvEmail.text = it.data?.email
                    }
                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                }

            })

        }

    }

    private fun isDataValid(): Boolean {
        if (etEnterId.text.isEmpty()) {
            etEnterId.error = "Field empty"
            return false
        }

        var isNumber = false
        try {
            etEnterId.text.toString().toInt()
            isNumber = true
        } catch (e: Exception) {
            isNumber = false
        }
        return isNumber
    }
}