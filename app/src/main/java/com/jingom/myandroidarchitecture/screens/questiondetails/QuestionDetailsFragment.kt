package com.jingom.myandroidarchitecture.screens.questiondetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.questions.FetchQuestionDetailsUseCase
import com.jingom.myandroidarchitecture.screens.common.ViewMvcFactory
import com.jingom.myandroidarchitecture.screens.common.controllers.BackPressedListener
import com.jingom.myandroidarchitecture.screens.common.screensnavigator.ScreensNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class QuestionDetailsFragment: Fragment(), QuestionDetailsViewMvc.Listener {

	companion object {
		private const val ARG_QUESTION_ID = "ARG_QUESTION_ID"

		fun newFragment(questionId: String): QuestionDetailsFragment {
			val arguments = Bundle().also {
				it.putString(ARG_QUESTION_ID, questionId)
			}

			return QuestionDetailsFragment().apply {
				this.arguments = arguments
			}
		}
	}

	private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

	@Inject
	lateinit var viewMvcFactory: ViewMvcFactory
	@Inject
	lateinit var fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase
	@Inject
	lateinit var screensNavigator: ScreensNavigator
	private lateinit var questionDetailsViewMvc: QuestionDetailsViewMvc
	private var questionId: String? = null

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		questionId = requireArguments().getString(ARG_QUESTION_ID)
		questionDetailsViewMvc = viewMvcFactory.getQuestionDetailsViewMvc(container)
		questionDetailsViewMvc.registerListener(this)

		return questionDetailsViewMvc.rootView
	}

	override fun onStart() {
		super.onStart()
		fetchQuestionDetails()
	}

	override fun onStop() {
		super.onStop()
		coroutineScope.coroutineContext.cancelChildren()
	}

	private fun fetchQuestionDetails() {
		questionId?.let {

			coroutineScope.launch {
				questionDetailsViewMvc.showProgressIndication()

				try {
					when (val result = fetchQuestionDetailsUseCase.fetchQuestionDetails(it)) {
						is FetchQuestionDetailsUseCase.Result.Success -> {
							questionDetailsViewMvc.bindQuestionDetails(result.question)
						}
						is FetchQuestionDetailsUseCase.Result.Failure -> {
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
		Toast.makeText(requireContext(), R.string.error_network_call_failed, Toast.LENGTH_SHORT).show()
	}

	override fun onNavigateUpButtonClicked() {
		screensNavigator.navigateUp()
	}
}