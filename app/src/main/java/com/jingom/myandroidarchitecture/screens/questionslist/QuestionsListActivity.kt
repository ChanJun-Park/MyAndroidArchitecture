package com.jingom.myandroidarchitecture.screens.questionslist

import android.os.Bundle
import com.jingom.myandroidarchitecture.screens.common.BaseActivity
import com.jingom.myandroidarchitecture.screens.common.ViewMvcFactory
import dagger.hilt.android.AndroidEntryPoint
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