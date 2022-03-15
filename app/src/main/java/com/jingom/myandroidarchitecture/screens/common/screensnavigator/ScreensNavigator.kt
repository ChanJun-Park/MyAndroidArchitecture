package com.jingom.myandroidarchitecture.screens.common.screensnavigator

import com.jingom.myandroidarchitecture.screens.common.controllers.FragmentHelper
import com.jingom.myandroidarchitecture.screens.questiondetails.QuestionDetailsFragment
import com.jingom.myandroidarchitecture.screens.questionslist.QuestionsListFragment
import javax.inject.Inject

class ScreensNavigator @Inject constructor(
	private val fragmentHelper: FragmentHelper
) {

	fun toQuestionDetails(questionId: String) {
		fragmentHelper.replaceFragment(QuestionDetailsFragment.newFragment(questionId))
	}

	fun toQuestionsList() {
		fragmentHelper.replaceFragmentAndClearHistory(QuestionsListFragment.newInstance())
	}

	fun navigateUp() {
		fragmentHelper.navigateUp()
	}
}