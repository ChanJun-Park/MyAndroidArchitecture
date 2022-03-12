package com.jingom.myandroidarchitecture.screens.questionslist

import android.os.Bundle
import android.widget.Toast
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.common.Constants
import com.jingom.myandroidarchitecture.networking.QuestionSchema
import com.jingom.myandroidarchitecture.networking.QuestionsListResponseSchema
import com.jingom.myandroidarchitecture.questions.Question
import com.jingom.myandroidarchitecture.screens.common.BaseActivity
import com.jingom.myandroidarchitecture.screens.common.ViewMvcFactory
import com.jingom.myandroidarchitecture.screens.questiondetails.QuestionDetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class QuestionsListActivity : BaseActivity(), QuestionsListViewMvc.Listener {

	private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)

	@Inject lateinit var fetchQuestionListUseCase: FetchQuestionListUseCase
	@Inject lateinit var viewMvcFactory: ViewMvcFactory

	private lateinit var questionsListViewMvc: QuestionsListViewMvc

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		questionsListViewMvc = viewMvcFactory.getQuestionListViewMvc(null)
		setContentView(questionsListViewMvc.rootView)

		questionsListViewMvc.registerListener(this)
	}

	override fun onStart() {
		super.onStart()
		fetchQuestions()
	}

	override fun onStop() {
		super.onStop()
		coroutineScope.coroutineContext.cancelChildren()
	}

	private fun fetchQuestions() {
		coroutineScope.launch {
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
				
			}
		}
	}

	private fun bindQuestions(questions: List<Question>) {
		questionsListViewMvc.bindQuestions(questions)
	}

	private fun onFetchQuestionsFailed() {
		Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show()
	}

	override fun onQuestionClicked(question: Question?) {
		question?.let {
			QuestionDetailsActivity.start(this, question.id)
		}
	}
}