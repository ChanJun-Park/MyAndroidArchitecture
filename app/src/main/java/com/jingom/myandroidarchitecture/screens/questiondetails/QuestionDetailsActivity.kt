package com.jingom.myandroidarchitecture.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.questions.FetchQuestionDetailsUseCase
import com.jingom.myandroidarchitecture.screens.common.ViewMvcFactory
import com.jingom.myandroidarchitecture.screens.common.screensnavigator.ScreensNavigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class QuestionDetailsActivity : AppCompatActivity(), QuestionDetailsViewMvc.Listener {

	private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

	@Inject lateinit var viewMvcFactory: ViewMvcFactory
	@Inject lateinit var fetchQuestionDetailsUseCase: FetchQuestionDetailsUseCase
	@Inject lateinit var screensNavigator: ScreensNavigator
	private lateinit var questionDetailsViewMvc: QuestionDetailsViewMvc
	private var questionId: String? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		questionId = intent.getStringExtra(EXTRA_QUESTION_ID)
		questionDetailsViewMvc = viewMvcFactory.getQuestionDetailsViewMvc(null)
		questionDetailsViewMvc.registerListener(this)

		setContentView(questionDetailsViewMvc.rootView)
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
		Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show()
	}

	companion object {
		private const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"

		fun start(context: Context, questionId: String) {
			Intent(context, QuestionDetailsActivity::class.java).also {
				it.putExtra(EXTRA_QUESTION_ID, questionId)
				context.startActivity(it)
			}
		}
	}

	override fun onNavigateUpButtonClicked() {
		screensNavigator.navigateBack()
	}

	override fun onQuestionsClicked() {
		screensNavigator.toQuestionsListClearTop()
	}

	override fun onBackPressed() {
		if (questionDetailsViewMvc.isDrawerOpen()) {
			questionDetailsViewMvc.closeDrawer()
		} else {
			super.onBackPressed()
		}
	}
}