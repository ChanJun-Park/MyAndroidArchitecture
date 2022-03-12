package com.jingom.myandroidarchitecture.networking

import com.google.gson.annotations.SerializedName
import com.jingom.myandroidarchitecture.questions.QuestionDetails

data class QuestionSchema(
	@field:SerializedName("title") val title: String,
	@field:SerializedName("question_id") val id: String,
	@field:SerializedName("body") val body: String,
	@field:SerializedName("owner") val owner: UserSchema
) {
	fun toQuestionDetails(): QuestionDetails = QuestionDetails(id, title, body)
}