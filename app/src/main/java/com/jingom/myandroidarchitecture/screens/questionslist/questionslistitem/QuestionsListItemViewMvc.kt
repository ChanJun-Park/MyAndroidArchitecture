package com.jingom.myandroidarchitecture.screens.questionslist.questionslistitem

import com.jingom.myandroidarchitecture.questions.Question
import com.jingom.myandroidarchitecture.screens.common.view.ObservableViewMvc

interface QuestionsListItemViewMvc: ObservableViewMvc<QuestionsListItemViewMvc.Listener> {

	interface Listener{
		fun onQuestionClicked(question: Question?)
	}

	fun bindQuestion(question: Question?)
}