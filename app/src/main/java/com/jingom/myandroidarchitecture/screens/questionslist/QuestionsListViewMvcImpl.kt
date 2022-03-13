package com.jingom.myandroidarchitecture.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.questions.Question
import com.jingom.myandroidarchitecture.screens.common.view.BaseObservableViewMvc

class QuestionsListViewMvcImpl(
	layoutInflater: LayoutInflater,
	parent: ViewGroup?
) : BaseObservableViewMvc<QuestionsListViewMvc.Listener>(
	layoutInflater.inflate(R.layout.layout_questions_list, parent, false)
), QuestionsListAdapter.OnQuestionClickListener, QuestionsListViewMvc {

	private var mLstQuestions: RecyclerView = findViewById(R.id.lst_questions)
	private var progressBar: ProgressBar = findViewById(R.id.progress_bar)
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

	override fun showProgressBar() {
		progressBar.visibility = View.VISIBLE
	}

	override fun hideProgressBar() {
		progressBar.visibility = View.GONE
	}
}