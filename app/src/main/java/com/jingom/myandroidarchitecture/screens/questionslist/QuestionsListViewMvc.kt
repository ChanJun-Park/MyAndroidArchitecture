package com.jingom.myandroidarchitecture.screens.questionslist

import android.view.View
import com.jingom.myandroidarchitecture.questions.Question

interface QuestionsListViewMvc {
	val rootView: View

	interface Listener{
		fun onQuestionClicked(question: Question)
	}

	fun registerListener(listener: Listener)
	fun unregisterListener(listener: Listener)
	fun bindQuestions(questions: MutableList<Question>)
}