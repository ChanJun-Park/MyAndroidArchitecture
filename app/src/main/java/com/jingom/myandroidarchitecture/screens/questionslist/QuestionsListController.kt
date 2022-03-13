package com.jingom.myandroidarchitecture.screens.questionslist

import com.jingom.myandroidarchitecture.questions.Question
import com.jingom.myandroidarchitecture.screens.common.MessageDisplayer
import com.jingom.myandroidarchitecture.screens.common.ScreensNavigator
import kotlinx.coroutines.*
import javax.inject.Inject

class QuestionsListController @Inject constructor(
	private val screensNavigator: ScreensNavigator,
	private val messageDisplayer: MessageDisplayer,
	private val fetchQuestionListUseCase: FetchQuestionListUseCase
) : QuestionsListViewMvc.Listener {

	private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
	private lateinit var questionsListViewMvc: QuestionsListViewMvc

	fun bindView(questionsListViewMvc: QuestionsListViewMvc) {
		this.questionsListViewMvc = questionsListViewMvc
		questionsListViewMvc.registerListener(this)
	}

	fun onStart() {
		fetchQuestions()
	}

	fun onStop() {
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
		messageDisplayer.showFetchError()
	}

	override fun onQuestionClicked(question: Question?) {
		question?.let {
			screensNavigator.navigateToQuestionDetails(question.id)
		}
	}
}