package com.xyz.retrofit_kotlin

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*

class PostViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setData(responseModel: ResponseModel) {
        view.apply {
            tvTitle.text = responseModel.title
            tvBody.text = responseModel.body
        }
    }
}