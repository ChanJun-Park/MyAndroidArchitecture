package com.jingom.myandroidarchitecture.screens.questionslist

import com.jingom.myandroidarchitecture.questions.Question
import com.jingom.myandroidarchitecture.screens.common.ViewMvc

interface QuestionsListViewMvc: ViewMvc {
	interface Listener{
		fun onQuestionClicked(question: Question?)
	}

	fun registerListener(listener: Listener)
	fun unregisterListener(listener: Listener)
	fun bindQuestions(questions: MutableList<Question>)
}