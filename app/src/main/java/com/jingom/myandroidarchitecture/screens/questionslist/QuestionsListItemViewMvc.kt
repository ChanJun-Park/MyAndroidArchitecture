package com.jingom.myandroidarchitecture.screens.questionslist

import com.jingom.myandroidarchitecture.questions.Question
import com.jingom.myandroidarchitecture.screens.common.ViewMvc

interface QuestionsListItemViewMvc: ViewMvc {

	interface Listener{
		fun onQuestionClicked(question: Question?)
	}

	fun bindQuestion(question: Question?)

	fun registerListener(listener: Listener)

	fun unregisterListener(listener: Listener)
}