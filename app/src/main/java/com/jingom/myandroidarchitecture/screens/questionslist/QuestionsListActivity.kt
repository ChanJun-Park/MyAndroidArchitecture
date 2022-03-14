package com.jingom.myandroidarchitecture.screens.questionslist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.jingom.myandroidarchitecture.screens.common.BaseActivity
import com.jingom.myandroidarchitecture.screens.common.ViewMvcFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class QuestionsListActivity : BaseActivity() {

	companion object {
		fun startAndClearTop(context: Context) {
			Intent(context, QuestionsListActivity::class.java).let {
				it.flags = it.flags or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
				context.startActivity(it)
			}
		}
	}

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

	override fun onBackPressed() {
		if (!questionsListController.onBackPressed()) {
			super.onBackPressed()
		}
	}
}