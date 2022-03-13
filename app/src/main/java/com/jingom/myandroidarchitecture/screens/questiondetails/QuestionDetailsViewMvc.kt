package com.jingom.myandroidarchitecture.screens.questiondetails

import com.jingom.myandroidarchitecture.questions.QuestionDetails
import com.jingom.myandroidarchitecture.screens.common.view.ObservableViewMvc
import com.jingom.myandroidarchitecture.screens.common.view.ViewMvc

interface QuestionDetailsViewMvc: ObservableViewMvc<QuestionDetailsViewMvc.Listener> {
	interface Listener {
		fun onNavigateUpButtonClicked()
	}

	fun bindQuestionDetails(questionDetails: QuestionDetails)
	fun showProgressIndication()
	fun hideProgressIndication()
}