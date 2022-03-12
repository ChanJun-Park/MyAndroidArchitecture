package com.jingom.myandroidarchitecture.screens.questionslist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListView
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.questions.Question
import com.jingom.myandroidarchitecture.screens.common.BaseViewMvc

class QuestionsListViewMvcImpl(
	layoutInflater: LayoutInflater,
	parent: ViewGroup?
) : BaseViewMvc(
	layoutInflater.inflate(R.layout.layout_questions_list, parent, false)
), QuestionsListAdapter.OnQuestionClickListener, QuestionsListViewMvc {

	private val listeners = ArrayList<QuestionsListViewMvc.Listener>(1)
	private var mLstQuestions: ListView = findViewById(R.id.lst_questions)
	private var mQuestionsListAdapter: QuestionsListAdapter = QuestionsListAdapter(rootView.context, this)

	init {
		mLstQuestions.adapter = mQuestionsListAdapter
	}

	override fun registerListener(listener: QuestionsListViewMvc.Listener) {
		listeners.add(listener)
	}

	override fun unregisterListener(listener: QuestionsListViewMvc.Listener) {
		listeners.remove(listener)
	}

	override fun onQuestionClicked(question: Question?) {
		listeners.forEach {
			it.onQuestionClicked(question)
		}
	}

	override fun bindQuestions(questions: MutableList<Question>) {
		mQuestionsListAdapter.clear()
		mQuestionsListAdapter.addAll(questions)
		mQuestionsListAdapter.notifyDataSetChanged()
	}
}