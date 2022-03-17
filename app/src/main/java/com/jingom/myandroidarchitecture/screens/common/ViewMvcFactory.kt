package com.jingom.myandroidarchitecture.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.jingom.myandroidarchitecture.screens.common.dialogs.promptdialogs.PromptViewMvc
import com.jingom.myandroidarchitecture.screens.common.dialogs.promptdialogs.PromptViewMvcImpl
import com.jingom.myandroidarchitecture.screens.common.navdrawer.NavDrawerHelper
import com.jingom.myandroidarchitecture.screens.common.navdrawer.NavDrawerViewMvc
import com.jingom.myandroidarchitecture.screens.common.navdrawer.NavDrawerViewMvcImpl
import com.jingom.myandroidarchitecture.screens.common.toolbar.ToolbarViewMvc
import com.jingom.myandroidarchitecture.screens.questiondetails.QuestionDetailsViewMvc
import com.jingom.myandroidarchitecture.screens.questiondetails.QuestionDetailsViewMvcImpl
import com.jingom.myandroidarchitecture.screens.questionslist.QuestionsListViewMvc
import com.jingom.myandroidarchitecture.screens.questionslist.QuestionsListViewMvcImpl
import javax.inject.Inject

class ViewMvcFactory @Inject constructor(
	private val layoutInflater: LayoutInflater,
	private val navDrawerHelper: NavDrawerHelper
) {
	fun getQuestionListViewMvc(parent: ViewGroup?): QuestionsListViewMvc {
		return QuestionsListViewMvcImpl(layoutInflater, parent, this, navDrawerHelper)
	}

	fun getQuestionDetailsViewMvc(parent: ViewGroup?): QuestionDetailsViewMvc {
		return QuestionDetailsViewMvcImpl(layoutInflater, parent, this)
	}

	fun getToolbarViewMvc(toolbar: Toolbar): ToolbarViewMvc {
		return ToolbarViewMvc(layoutInflater, toolbar)
	}

	fun getNavDrawerViewMvc(parent: ViewGroup?): NavDrawerViewMvc {
		return NavDrawerViewMvcImpl(layoutInflater, parent)
	}

	fun getPromptViewMvc(): PromptViewMvc {
		return PromptViewMvcImpl(layoutInflater, null)
	}
}