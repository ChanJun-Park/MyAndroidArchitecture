package com.jingom.myandroidarchitecture.screens.questionslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.questions.Question
import com.jingom.myandroidarchitecture.screens.common.BaseObservableViewMvc

class QuestionsListViewMvcImpl(
	layoutInflater: LayoutInflater,
	parent: ViewGroup?
) : BaseObservableViewMvc<QuestionsListViewMvc.Listener>(
	layoutInflater.inflate(R.layout.layout_questions_list, parent, false)
), QuestionsListAdapter.OnQuestionClickListener, QuestionsListViewMvc {

	private var mLstQuestions: RecyclerView = findViewById(R.id.lst_questions)
	private var mQuestionsListAdapter: QuestionsListAdapter = QuestionsListAdapter(layoutInflater, this)

	init {
		mLstQuestions.adapter = mQuestionsListAdapter
	}

	override fun onQuestionClicked(question: Question?) {
		getListeners().forEach {
			it.onQuestionClicked(question)
		}
	}

	override fun bindQuestions(questions: List<Question>) {
		mQuestionsListAdapter.questions = questions
	}
}