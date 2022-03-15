package com.jingom.myandroidarchitecture.screens.questiondetails

import com.jingom.myandroidarchitecture.questions.QuestionDetails
import com.jingom.myandroidarchitecture.screens.common.navdrawer.NavDrawerViewMvc
import com.jingom.myandroidarchitecture.screens.common.views.ObservableViewMvc

interface QuestionDetailsViewMvc: ObservableViewMvc<QuestionDetailsViewMvc.Listener> {
	interface Listener {
		fun onNavigateUpButtonClicked()
	}

	fun bindQuestionDetails(questionDetails: QuestionDetails)
	fun showProgressIndication()
	fun hideProgressIndication()
}