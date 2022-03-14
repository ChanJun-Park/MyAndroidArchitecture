package com.jingom.myandroidarchitecture.screens.questionslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jingom.myandroidarchitecture.screens.common.ViewMvcFactory
import com.jingom.myandroidarchitecture.screens.common.controllers.BackPressedListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class QuestionsListFragment: Fragment(), BackPressedListener {
	@Inject lateinit var viewMvcFactory: ViewMvcFactory
	@Inject lateinit var questionsListController: QuestionsListController

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		val questionsListViewMvc = viewMvcFactory.getQuestionListViewMvc(container)
		questionsListController.bindView(questionsListViewMvc)

		return questionsListViewMvc.rootView
	}

	override fun onStart() {
		super.onStart()
		questionsListController.onStart()
	}

	override fun onStop() {
		super.onStop()
		questionsListController.onStop()
	}

	override fun onBackPressed(): Boolean {
		return questionsListController.onBackPressed()
	}
}