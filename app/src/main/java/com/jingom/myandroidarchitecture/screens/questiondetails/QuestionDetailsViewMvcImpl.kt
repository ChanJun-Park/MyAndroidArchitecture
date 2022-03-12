package com.jingom.myandroidarchitecture.screens.questiondetails

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.ContentLoadingProgressBar
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.questions.QuestionDetails
import com.jingom.myandroidarchitecture.screens.common.BaseViewMvc

class QuestionDetailsViewMvcImpl(
	layoutInflater: LayoutInflater,
	parent: ViewGroup?
) : BaseViewMvc(
	layoutInflater.inflate(R.layout.layout_question_details, parent, false)
), QuestionDetailsViewMvc {

	private val title: TextView = findViewById(R.id.title)
	private val body: TextView = findViewById(R.id.body)
	private val progressBar: ContentLoadingProgressBar = findViewById(R.id.progress_bar)
	private var questionDetails: QuestionDetails? = null

	override fun bindQuestionDetails(questionDetails: QuestionDetails) {
		this.questionDetails = questionDetails

		title.text = questionDetails.title
		body.text = questionDetails.body
	}

	override fun showProgressIndication() {
		progressBar.show()
	}

	override fun hideProgressIndication() {
		progressBar.hide()
	}
}