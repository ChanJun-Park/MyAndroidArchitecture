package com.jingom.myandroidarchitecture.screens.questionslist

import com.jingom.myandroidarchitecture.questions.FetchQuestionListUseCase
import com.jingom.myandroidarchitecture.questions.Question
import com.jingom.myandroidarchitecture.screens.common.screensnavigator.ScreensNavigator
import com.jingom.myandroidarchitecture.screens.common.toastshelper.ToastHelper
import kotlinx.coroutines.*
import javax.inject.Inject

class QuestionsListController @Inject constructor(
	private val screensNavigator: ScreensNavigator,
	private val toastHelper: ToastHelper,
	private val fetchQuestionListUseCase: FetchQuestionListUseCase,
) : QuestionsListViewMvc.Listener {

	private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
	private lateinit var questionsListViewMvc: QuestionsListViewMvc

	fun bindView(questionsListViewMvc: QuestionsListViewMvc) {
		this.questionsListViewMvc = questionsListViewMvc
	}

	fun onStart() {
		questionsListViewMvc.registerListener(this)
		fetchQuestions()
	}

	fun onStop() {
		questionsListViewMvc.unregisterListener(this)
		coroutineScope.coroutineContext.cancelChildren()
	}

	private fun fetchQuestions() {
		coroutineScope.launch {

			questionsListViewMvc.showProgressBar()

			try {
				when (val result = fetchQuestionListUseCase.fetchLastActiveQuestions()) {
					is FetchQuestionListUseCase.Result.Success -> {
						bindQuestions(result.questions)
					}
					is FetchQuestionListUseCase.Result.Failure -> {
						onFetchQuestionsFailed()
					}
				}
			} finally {
				questionsListViewMvc.hideProgressBar()
			}
		}
	}

	private fun bindQuestions(questions: List<Question>) {
		questionsListViewMvc.bindQuestions(questions)
	}

	private fun onFetchQuestionsFailed() {
		toastHelper.showFetchError()
	}

	override fun onQuestionClicked(question: Question?) {
		question?.let {
			screensNavigator.toQuestionDetails(question.id)
		}
	}
}