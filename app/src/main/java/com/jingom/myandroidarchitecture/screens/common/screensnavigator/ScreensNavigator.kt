package com.jingom.myandroidarchitecture.screens.common.screensnavigator

import androidx.fragment.app.FragmentManager
import com.jingom.myandroidarchitecture.screens.common.controllers.FragmentFrameWrapper
import com.jingom.myandroidarchitecture.screens.questiondetails.QuestionDetailsFragment
import com.jingom.myandroidarchitecture.screens.questionslist.QuestionsListFragment
import javax.inject.Inject

class ScreensNavigator @Inject constructor(
	private val fragmentManager: FragmentManager,
	private val fragmentFrameWrapper: FragmentFrameWrapper
) {

	fun toQuestionDetails(questionId: String) {
		fragmentManager.beginTransaction().addToBackStack(null)
			.replace(fragmentFrameWrapper.getFragmentFrame().id, QuestionDetailsFragment.newFragment(questionId))
			.commit()
	}

	fun toQuestionsList() {
		fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
		fragmentManager.beginTransaction().replace(
			fragmentFrameWrapper.getFragmentFrame().id, QuestionsListFragment.newInstance()
		).commit()
	}
}