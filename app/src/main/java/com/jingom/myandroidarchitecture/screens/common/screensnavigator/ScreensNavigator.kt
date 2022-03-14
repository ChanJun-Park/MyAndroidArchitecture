package com.jingom.myandroidarchitecture.screens.common.screensnavigator

import android.app.Activity
import android.content.Context
import com.jingom.myandroidarchitecture.common.dependecyinjection.ActivityContextQualifier
import com.jingom.myandroidarchitecture.screens.questiondetails.QuestionDetailsActivity
import com.jingom.myandroidarchitecture.screens.questionslist.QuestionsListActivity
import com.jingom.myandroidarchitecture.screens.questionslist.QuestionsListViewMvcImpl
import javax.inject.Inject

class ScreensNavigator @Inject constructor(
	@ActivityContextQualifier private val context: Context
) {

	fun navigateToQuestionDetails(questionId: String) {
		QuestionDetailsActivity.start(context, questionId)
	}

	fun navigateBack() {
		(context as Activity).onBackPressed()
	}

	fun toQuestionsListClearTop() {
		QuestionsListActivity.startAndClearTop(context)
	}
}