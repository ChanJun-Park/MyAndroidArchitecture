package com.jingom.myandroidarchitecture.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.IdRes
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.questions.Question

class QuestionsListItemViewMvcImpl(
	layoutInflater: LayoutInflater,
	parent: ViewGroup
): QuestionsListItemViewMvc {

	override val rootView: View = layoutInflater.inflate(R.layout.layout_question_list_item, parent, false).also {
		it.setOnClickListener {
			listeners.forEach { listener ->
				listener.onQuestionClicked(question)
			}
		}
	}

	private val mTxtTitle: TextView = findViewById(R.id.txt_title)
	private var question: Question? = null

	private val listeners = ArrayList<QuestionsListItemViewMvc.Listener>()

	override fun bindQuestion(question: Question?) {
		this.question = question
		this.mTxtTitle.text = question?.title
	}

	override fun registerListener(listener: QuestionsListItemViewMvc.Listener) {
		listeners.add(listener)
	}

	override fun unregisterListener(listener: QuestionsListItemViewMvc.Listener) {
		listeners.remove(listener)
	}

	private fun <T : View?> findViewById(@IdRes id: Int): T {
		return rootView.findViewById<T>(id)
	}
}