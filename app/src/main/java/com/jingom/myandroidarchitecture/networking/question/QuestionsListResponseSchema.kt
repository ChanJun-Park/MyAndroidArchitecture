package com.jingom.myandroidarchitecture.networking.question

import com.google.gson.annotations.SerializedName

data class QuestionsListResponseSchema(
	@field:SerializedName("items") val questions: List<QuestionSchema>
)