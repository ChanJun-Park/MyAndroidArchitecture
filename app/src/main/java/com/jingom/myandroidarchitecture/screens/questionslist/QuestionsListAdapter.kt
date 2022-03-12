package com.jingom.myandroidarchitecture.screens.questionslist

import android.content.Context
import android.widget.ArrayAdapter
import com.jingom.myandroidarchitecture.questions.Question
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.jingom.myandroidarchitecture.R

class QuestionsListAdapter(
	context: Context,
	private val mOnQuestionClickListener: OnQuestionClickListener
) : ArrayAdapter<Question>(context, 0) {

	interface OnQuestionClickListener {
		fun onQuestionClicked(question: Question)
	}

	override fun getView(position: Int, view: View?, parent: ViewGroup): View {
		var convertView = view
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.context)
				.inflate(R.layout.layout_question_list_item, parent, false)
		}
		val question = getItem(position)

		// bind the data to views
		val txtTitle = convertView!!.findViewById<TextView>(R.id.txt_title)
		txtTitle.text = question!!.title

		// set listener
		convertView.setOnClickListener { onQuestionClicked(question) }
		return convertView
	}

	private fun onQuestionClicked(question: Question) {
		mOnQuestionClickListener.onQuestionClicked(question)
	}
}