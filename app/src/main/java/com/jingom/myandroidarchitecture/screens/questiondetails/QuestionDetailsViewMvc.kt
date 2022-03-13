package com.jingom.myandroidarchitecture.screens.questiondetails

import com.jingom.myandroidarchitecture.questions.QuestionDetails
import com.jingom.myandroidarchitecture.screens.common.view.ViewMvc

interface QuestionDetailsViewMvc: ViewMvc {
	fun bindQuestionDetails(questionDetails: QuestionDetails)
	fun showProgressIndication()
	fun hideProgressIndication()
}