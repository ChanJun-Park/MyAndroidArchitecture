package com.jingom.myandroidarchitecture.screens.questiondetails

import com.jingom.myandroidarchitecture.networking.StackoverflowApi
import com.jingom.myandroidarchitecture.questions.QuestionDetails
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchQuestionDetailsUseCase @Inject constructor(private val stackoverflowApi: StackoverflowApi) {

	sealed class Result {
		data class Success(val question: QuestionDetails) : Result()
		object Failure: Result()
	}

	suspend fun fetchQuestionDetails(questionId: String?): Result {
		return withContext(Dispatchers.IO) {
			try {
				val response = stackoverflowApi.fetchQuestionDetails(questionId)
				val questionSchema = response.body()?.question

				if (response.isSuccessful && questionSchema != null) {
					Result.Success(questionSchema.toQuestionDetails())
				} else {
					Result.Failure
				}
			} catch (t: Throwable) {
				if (t !is CancellationException) {
					Result.Failure
				} else {
					throw t
				}
			}
		}
	}
}