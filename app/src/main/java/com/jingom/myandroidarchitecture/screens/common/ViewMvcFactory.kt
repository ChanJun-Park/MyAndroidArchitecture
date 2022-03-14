package com.jingom.myandroidarchitecture.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.jingom.myandroidarchitecture.screens.common.toolbar.ToolbarViewMvc
import com.jingom.myandroidarchitecture.screens.questiondetails.QuestionDetailsViewMvc
import com.jingom.myandroidarchitecture.screens.questiondetails.QuestionDetailsViewMvcImpl
import com.jingom.myandroidarchitecture.screens.questionslist.QuestionsListViewMvc
import com.jingom.myandroidarchitecture.screens.questionslist.QuestionsListViewMvcImpl
import javax.inject.Inject

class ViewMvcFactory @Inject constructor(
	private val layoutInflater: LayoutInflater
) {
	fun getQuestionListViewMvc(parent: ViewGroup?): QuestionsListViewMvc {
		return QuestionsListViewMvcImpl(layoutInflater, parent, this)
	}

	fun getQuestionDetailsViewMvc(parent: ViewGroup?): QuestionDetailsViewMvc {
		return QuestionDetailsViewMvcImpl(layoutInflater, parent, this)
	}

	fun getToolbarViewMvc(toolbar: Toolbar): ToolbarViewMvc {
		return ToolbarViewMvc(layoutInflater, toolbar)
	}
}