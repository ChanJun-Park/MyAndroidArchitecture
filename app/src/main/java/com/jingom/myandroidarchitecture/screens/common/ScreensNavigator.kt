package com.jingom.myandroidarchitecture.screens.common

import android.content.Context
import com.jingom.myandroidarchitecture.common.dependecyinjection.ActivityContextQualifier
import com.jingom.myandroidarchitecture.screens.questiondetails.QuestionDetailsActivity
import javax.inject.Inject

class ScreensNavigator @Inject constructor(
	@ActivityContextQualifier private val context: Context
) {

	fun navigateToQuestionDetails(questionId: String) {
		QuestionDetailsActivity.start(context, questionId)
	}
}