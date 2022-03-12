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

	private class ViewHolder {
		lateinit var mTxtTitle: TextView
	}

	override fun getView(position: Int, view: View?, parent: ViewGroup): View {
		var convertView = view
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.context)
				.inflate(R.layout.layout_question_list_item, parent, false)

			val viewHolder = ViewHolder().apply {
				mTxtTitle = convertView.findViewById(R.id.txt_title)
			}

			convertView.tag = viewHolder
		}
		val question = getItem(position)

		// bind the data to views
		val viewHolder = convertView!!.tag as ViewHolder
		viewHolder.mTxtTitle.text = question!!.title

		// set listener
		convertView.setOnClickListener { onQuestionClicked(question) }
		return convertView
	}

	private fun onQuestionClicked(question: Question) {
		mOnQuestionClickListener.onQuestionClicked(question)
	}
}