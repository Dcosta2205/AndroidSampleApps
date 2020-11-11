package com.xyz.apicall

import com.google.gson.annotations.SerializedName

data class ResponseModel(

	@field:SerializedName("data")
	val data: DataModel? = null,

	@field:SerializedName("ad")
	val ad: AdModel? = null
)