package com.jingom.myandroidarchitecture.networking

import com.jingom.myandroidarchitecture.common.Constants
import retrofit2.http.GET
import com.jingom.myandroidarchitecture.networking.QuestionsListResponseSchema
import retrofit2.Call
import retrofit2.http.Query

interface StackoverflowApi {
	@GET("/questions?key=" + Constants.STACKOVERFLOW_API_KEY + "&sort=activity&order=desc&site=stackoverflow&filter=withbody")
	fun fetchLastActiveQuestions(@Query("pagesize") pageSize: Int): Call<QuestionsListResponseSchema?>
}