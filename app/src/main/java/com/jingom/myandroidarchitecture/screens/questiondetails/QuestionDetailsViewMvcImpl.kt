package com.jingom.myandroidarchitecture.screens.questiondetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.questions.QuestionDetails
import com.jingom.myandroidarchitecture.screens.common.ToolbarViewMvc
import com.jingom.myandroidarchitecture.screens.common.ViewMvcFactory
import com.jingom.myandroidarchitecture.screens.common.view.BaseObservableViewMvc
import com.jingom.myandroidarchitecture.screens.common.view.BaseViewMvc

class QuestionDetailsViewMvcImpl(
	layoutInflater: LayoutInflater,
	parent: ViewGroup?,
	viewMvcFactory: ViewMvcFactory
) : BaseObservableViewMvc<QuestionDetailsViewMvc.Listener>(
	layoutInflater.inflate(R.layout.layout_question_details, parent, false)
), QuestionDetailsViewMvc {

	private var questionDetails: QuestionDetails? = null

	private val title: TextView = findViewById(R.id.title)
	private val body: TextView = findViewById(R.id.body)
	private val progressBar: ProgressBar = findViewById(R.id.progress_bar)

	private val toolbar: Toolbar = findViewById(R.id.toolbar)
	private val toolbarViewMvc: ToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(toolbar).also {
		it.setTitle(toolbar.resources.getString(R.string.question_details_screen_title))
		it.enableNavigationUpButtonAndListen(object: ToolbarViewMvc.NavigationUpClickListener {
			override fun onNavigationUpClicked() {
				getListeners().forEach { listener ->
					listener.onNavigateUpButtonClicked()
				}
			}
		})
		toolbar.addView(it.rootView)
	}

	override fun bindQuestionDetails(questionDetails: QuestionDetails) {
		this.questionDetails = questionDetails

		title.text = questionDetails.title
		body.text = questionDetails.body
	}

	override fun showProgressIndication() {
		progressBar.visibility = View.VISIBLE
	}

	override fun hideProgressIndication() {
		progressBar.visibility = View.GONE
	}
}