package com.jingom.myandroidarchitecture.screens.questionslist

import com.jingom.myandroidarchitecture.questions.Question
import com.jingom.myandroidarchitecture.screens.common.ObservableViewMvc

interface QuestionsListViewMvc: ObservableViewMvc<QuestionsListViewMvc.Listener> {
	interface Listener{
		fun onQuestionClicked(question: Question?)
	}

	fun bindQuestions(questions: List<Question>)

	fun showProgressBar()

	fun hideProgressBar()
}