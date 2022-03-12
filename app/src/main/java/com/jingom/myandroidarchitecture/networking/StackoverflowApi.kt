package com.jingom.myandroidarchitecture.networking

import com.jingom.myandroidarchitecture.common.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StackoverflowApi {
	@GET("/questions?key=" + Constants.STACKOVERFLOW_API_KEY + "&sort=activity&order=desc&site=stackoverflow&filter=withbody")
	suspend fun fetchLastActiveQuestions(@Query("pagesize") pageSize: Int): Response<QuestionsListResponseSchema?>

	@GET("/questions/{questionId}?key=" + Constants.STACKOVERFLOW_API_KEY + "&site=stackoverflow&filter=withbody")
	suspend fun fetchQuestionDetails(@Path("questionId") questionId: String?): Response<QuestionDetailsResponseSchema?>

}