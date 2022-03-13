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
class QuestionsListActivity : BaseActivity() {

	@Inject lateinit var viewMvcFactory: ViewMvcFactory
	@Inject lateinit var questionsListController: QuestionsListController

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val questionsListViewMvc = viewMvcFactory.getQuestionListViewMvc(null)
		questionsListController.bindView(questionsListViewMvc)

		setContentView(questionsListViewMvc.rootView)
	}

	override fun onStart() {
		super.onStart()
		questionsListController.onStart()
	}

	override fun onStop() {
		super.onStop()
		questionsListController.onStop()
	}
}