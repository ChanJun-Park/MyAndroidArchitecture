package com.jingom.myandroidarchitecture.screens.questionslist

import com.jingom.myandroidarchitecture.questions.FetchQuestionListUseCase
import com.jingom.myandroidarchitecture.questions.Question
import com.jingom.myandroidarchitecture.screens.common.dialogs.DialogsEventBus
import com.jingom.myandroidarchitecture.screens.common.dialogs.DialogsManager
import com.jingom.myandroidarchitecture.screens.common.dialogs.promptdialogs.PromptDialogEvent
import com.jingom.myandroidarchitecture.screens.common.screensnavigator.ScreensNavigator
import kotlinx.coroutines.*
import java.io.Serializable
import javax.inject.Inject

class QuestionsListController @Inject constructor(
	private val screensNavigator: ScreensNavigator,
	private var dialogsManager: DialogsManager,
	private val fetchQuestionListUseCase: FetchQuestionListUseCase,
	private val dialogEventBus: DialogsEventBus
) : QuestionsListViewMvc.Listener, DialogsEventBus.Listener {

	companion object {
		const val SAVED_STATE_SCREEN_STATE = "SAVED_STATE_SCREEN_STATE"
		private const val DIALOG_ID_NETWORK_ERROR = "DIALOG_ID_NETWORK_ERROR"
	}

	private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
	private lateinit var questionsListViewMvc: QuestionsListViewMvc

	private var screenState = ScreenState.IDLE

	enum class ScreenState {
		IDLE, QUESTIONS_LIST_SHOWN, NETWORK_ERROR
	}

	fun bindView(questionsListViewMvc: QuestionsListViewMvc) {
		this.questionsListViewMvc = questionsListViewMvc
	}

	fun onStart() {
		dialogEventBus.registerListener(this)
		questionsListViewMvc.registerListener(this)

		if (screenState != ScreenState.NETWORK_ERROR) {
			fetchQuestions()
		}
	}

	fun onStop() {
		dialogEventBus.unregisterListener(this)
		questionsListViewMvc.unregisterListener(this)
		coroutineScope.coroutineContext.cancelChildren()
	}

	private fun fetchQuestions() {
		coroutineScope.launch {

			questionsListViewMvc.showProgressBar()

			try {
				when (val result = fetchQuestionListUseCase.fetchLastActiveQuestions()) {
					is FetchQuestionListUseCase.Result.Success -> {
						screenState = ScreenState.QUESTIONS_LIST_SHOWN
						bindQuestions(result.questions)
					}
					is FetchQuestionListUseCase.Result.Failure -> {
						screenState = ScreenState.NETWORK_ERROR
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
		dialogsManager.showUseCaseErrorDialog(DIALOG_ID_NETWORK_ERROR)
	}

	override fun onQuestionClicked(question: Question?) {
		question?.let {
			screensNavigator.toQuestionDetails(question.id)
		}
	}

	override fun onDialogEvent(event: Any) {
		(event as? PromptDialogEvent)?.let {
			when (it.clickedButton) {
				PromptDialogEvent.Button.POSITIVE -> {
					screenState = ScreenState.IDLE
					fetchQuestions()
				}
				PromptDialogEvent.Button.NEGATIVE -> {
					screenState = ScreenState.IDLE
				}
			}
		}
	}

	fun restoreSavedState(savedState: SavedState) {
		this.screenState = savedState.screenState
	}

	fun getSavedState(): SavedState {
		return SavedState(screenState)
	}

	data class SavedState(
		val screenState: ScreenState
	): Serializable
}