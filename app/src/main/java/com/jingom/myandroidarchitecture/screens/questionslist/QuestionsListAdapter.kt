package com.jingom.myandroidarchitecture.screens.questionslist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jingom.myandroidarchitecture.questions.Question

class QuestionsListAdapter(
	private val layoutInflater: LayoutInflater,
	private val listener: OnQuestionClickListener
) : RecyclerView.Adapter<QuestionsListAdapter.MyViewHolder>(), QuestionsListItemViewMvc.Listener {

	interface OnQuestionClickListener {
		fun onQuestionClicked(question: Question?)
	}

	class MyViewHolder(val viewMvc: QuestionsListItemViewMvc): RecyclerView.ViewHolder(viewMvc.rootView) {

		companion object {
			fun from(layoutInflater: LayoutInflater, parent: ViewGroup, listener: QuestionsListItemViewMvc.Listener): MyViewHolder {
				val viewMvc = QuestionsListItemViewMvcImpl(layoutInflater, parent).apply {
					registerListener(listener)
				}
				return MyViewHolder(viewMvc)
			}
		}
	}

	var questions: List<Question> = ArrayList()
		@SuppressLint("NotifyDataSetChanged")
		set(value) {
			field = value
			notifyDataSetChanged()
		}

	override fun onQuestionClicked(question: Question?) {
		listener.onQuestionClicked(question)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
		return MyViewHolder.from(layoutInflater, parent, this)
	}

	override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
		if (position < 0 || position >= questions.size) {
			return
		}

		holder.viewMvc.bindQuestion(questions[position])
	}

	override fun getItemCount(): Int {
		return questions.size
	}
}