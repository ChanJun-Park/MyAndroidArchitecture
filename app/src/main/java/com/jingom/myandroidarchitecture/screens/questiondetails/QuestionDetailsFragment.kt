package com.jingom.myandroidarchitecture.screens.questiondetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jingom.myandroidarchitecture.questions.FetchQuestionDetailsUseCase
import com.jingom.myandroidarchitecture.screens.common.ViewMvcFactory
import com.jingom.myandroidarchitecture.screens.common.dialogs.DialogsEventBus
import com.jingom.myandroidarchitecture.screens.common.dialogs.DialogsManager
import com.jingom.myandroidarchitecture.screens.common.dialogs.promptdialogs.PromptDialogEvent
import com.jingom.myandroidarchitecture.screens.common.screensnavigator.ScreensNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class QuestionDetailsFragment: Fragment(), QuestionDetailsViewMvc.Listener, DialogsEventBus.Listener {

	companion object {
		private const val ARG_QUESTION_ID = "ARG_QUESTION_ID"
		private const val DIALOG_ID_NETWORK_ERROR = "DIALOG_ID_NETWORK_ERROR"
		private const val SAVED_STATE_SCREEN_STATE = "SAVED_STATE_SCREEN_STATE"

		fun newFragment(questionId: String): QuestionDetailsFragment {
			val arguments = Bundle().also {
				it.putString(ARG_QUESTION_ID, questionId)
			}

			return QuestionDetailsFragment().apply {
				this.arguments = arguments
			}
		}
	}

	private enum class ScreenState {
		IDLE, QUESTION_DETAILS_SHOWN, NETWORK_ERROR
	}

	private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

	@Inject lateinit var viewMvcFactory: ViewMvcFactory
	@Inject lateinit var fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase
	@Inject lateinit var screensNavigator: ScreensNavigator
	@Inject lateinit var dialogsManager: DialogsManager
	@Inject lateinit var dialogEventBus: DialogsEventBus

	private lateinit var questionDetailsViewMvc: QuestionDetailsViewMvc
	private var questionId: String? = null

	private var screenState = ScreenState.IDLE

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		savedInstanceState?.let {
			screenState = savedInstanceState.getSerializable(SAVED_STATE_SCREEN_STATE) as ScreenState
		}
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		questionId = requireArguments().getString(ARG_QUESTION_ID)
		questionDetailsViewMvc = viewMvcFactory.getQuestionDetailsViewMvc(container)
		questionDetailsViewMvc.registerListener(this)

		return questionDetailsViewMvc.rootView
	}

	override fun onStart() {
		super.onStart()
		dialogEventBus.registerListener(this)

		if (screenState != ScreenState.NETWORK_ERROR) {
			fetchQuestionDetails()
		}
	}

	override fun onStop() {
		super.onStop()
		coroutineScope.coroutineContext.cancelChildren()
		dialogEventBus.unregisterListener(this)
	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)
		outState.putSerializable(SAVED_STATE_SCREEN_STATE, screenState)
	}

	private fun fetchQuestionDetails() {
		questionId?.let {

			coroutineScope.launch {
				questionDetailsViewMvc.showProgressIndication()

				try {
					when (val result = fetchQuestionDetailsUseCase.fetchQuestionDetails(it)) {
						is FetchQuestionDetailsUseCase.Result.Success -> {
							screenState = ScreenState.QUESTION_DETAILS_SHOWN
							questionDetailsViewMvc.bindQuestionDetails(result.question)
						}
						is FetchQuestionDetailsUseCase.Result.Failure -> {
							screenState = ScreenState.NETWORK_ERROR
							onFetchFailed()
						}
					}
				} finally {
					questionDetailsViewMvc.hideProgressIndication()
				}
			}
		}
	}

	private fun onFetchFailed() {
		dialogsManager.showUseCaseErrorDialog(DIALOG_ID_NETWORK_ERROR)
	}

	override fun onNavigateUpButtonClicked() {
		screensNavigator.navigateUp()
	}

	override fun onDialogEvent(event: Any) {
		(event as? PromptDialogEvent)?.let {
			when (it.clickedButton) {
				PromptDialogEvent.Button.POSITIVE -> {
					screenState = ScreenState.IDLE
					fetchQuestionDetails()
				}
				PromptDialogEvent.Button.NEGATIVE -> {
					screenState = ScreenState.IDLE
				}
			}
		}
	}
}