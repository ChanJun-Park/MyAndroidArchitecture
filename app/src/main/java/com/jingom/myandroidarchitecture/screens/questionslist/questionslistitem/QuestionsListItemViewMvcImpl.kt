package com.jingom.myandroidarchitecture.screens.questionslist.questionslistitem

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.questions.Question
import com.jingom.myandroidarchitecture.screens.common.views.BaseObservableViewMvc

class QuestionsListItemViewMvcImpl(
	layoutInflater: LayoutInflater,
	parent: ViewGroup
): BaseObservableViewMvc<QuestionsListItemViewMvc.Listener>(
	layoutInflater.inflate(R.layout.layout_question_list_item, parent, false)
), QuestionsListItemViewMvc {

	init {
		rootView.setOnClickListener {
			getListeners().forEach { listener ->
				listener.onQuestionClicked(question)
			}
		}
	}

	private val mTxtTitle: TextView = findViewById(R.id.txt_title)
	private var question: Question? = null

	override fun bindQuestion(question: Question?) {
		this.question = question
		this.mTxtTitle.text = question?.title
	}
}