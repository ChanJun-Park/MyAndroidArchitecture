package com.jingom.myandroidarchitecture.screens.questionslist

import com.jingom.myandroidarchitecture.questions.Question
import com.jingom.myandroidarchitecture.screens.common.navdrawer.NavDrawerViewMvc
import com.jingom.myandroidarchitecture.screens.common.view.ObservableViewMvc

interface QuestionsListViewMvc: ObservableViewMvc<QuestionsListViewMvc.Listener>, NavDrawerViewMvc {
	interface Listener{
		fun onQuestionClicked(question: Question?)

		fun onQuestionsClicked()
	}

	fun bindQuestions(questions: List<Question>)

	fun showProgressBar()

	fun hideProgressBar()
}