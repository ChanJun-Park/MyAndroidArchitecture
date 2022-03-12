package com.jingom.myandroidarchitecture.networking

import com.google.gson.annotations.SerializedName

data class QuestionsListResponseSchema(
	@field:SerializedName("items") val questions: List<QuestionSchema>
)