package com.jingom.myandroidarchitecture.screens.questionslist

import android.content.Context
import android.widget.ArrayAdapter
import com.jingom.myandroidarchitecture.questions.Question
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jingom.myandroidarchitecture.R

class QuestionsListAdapter(
	context: Context,
	private val mOnQuestionClickListener: OnQuestionClickListener
) : ArrayAdapter<Question>(context, 0), QuestionsListItemViewMvc.Listener {

	interface OnQuestionClickListener {
		fun onQuestionClicked(question: Question?)
	}

	override fun getView(position: Int, view: View?, parent: ViewGroup): View {
		var convertView = view
		if (convertView == null) {
			val viewMvc = QuestionsListItemViewMvcImpl(LayoutInflater.from(context), parent).also {
				it.registerListener(this)
			}

			convertView = viewMvc.rootView
			convertView.tag = viewMvc
		}

		val question = getItem(position)

		// bind the data to views
		val viewMvc = convertView.tag as QuestionsListItemViewMvc
		viewMvc.bindQuestion(question)

		return convertView
	}

	override fun onQuestionClicked(question: Question?) {
		mOnQuestionClickListener.onQuestionClicked(question)
	}
}