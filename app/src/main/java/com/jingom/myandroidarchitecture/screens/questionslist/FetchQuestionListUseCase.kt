package com.jingom.myandroidarchitecture.screens.questionslist

import com.jingom.myandroidarchitecture.common.Constants
import com.jingom.myandroidarchitecture.networking.StackoverflowApi
import com.jingom.myandroidarchitecture.questions.Question
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FetchQuestionListUseCase @Inject constructor(private val stackoverflowApi: StackoverflowApi) {

	sealed class Result {
		data class Success(val questions: List<Question>) : Result()
		object Failure: Result()
	}

	suspend fun fetchLastActiveQuestions(): Result {
		return withContext(Dispatchers.IO) {
			try {
				val response = stackoverflowApi.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
				val questionSchemas = response.body()?.questions

				if (response.isSuccessful && questionSchemas != null) {
					val questions = mutableListOf<Question>()

					questionSchemas.forEach {
						questions.add(Question(it.id, it.title))
					}

					Result.Success(questions)
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