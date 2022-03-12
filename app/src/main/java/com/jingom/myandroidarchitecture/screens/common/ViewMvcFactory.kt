package com.jingom.myandroidarchitecture.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jingom.myandroidarchitecture.screens.questionslist.QuestionsListViewMvc
import com.jingom.myandroidarchitecture.screens.questionslist.QuestionsListViewMvcImpl
import javax.inject.Inject

class ViewMvcFactory @Inject constructor(
	private val layoutInflater: LayoutInflater
) {
	fun getQuestionListViewMvc(parent: ViewGroup?): QuestionsListViewMvc {
		return QuestionsListViewMvcImpl(layoutInflater, parent)
	}
}