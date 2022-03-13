package com.jingom.myandroidarchitecture.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.questions.Question
import com.jingom.myandroidarchitecture.screens.common.ToolbarViewMvc
import com.jingom.myandroidarchitecture.screens.common.ViewMvcFactory
import com.jingom.myandroidarchitecture.screens.common.navdrawer.BaseNavDrawerViewMvc
import com.jingom.myandroidarchitecture.screens.common.navdrawer.DrawerItem

class QuestionsListViewMvcImpl(
	layoutInflater: LayoutInflater,
	parent: ViewGroup?,
	viewMvcFactory: ViewMvcFactory
) : BaseNavDrawerViewMvc<QuestionsListViewMvc.Listener>(
	layoutInflater, parent, layoutInflater.inflate(R.layout.layout_questions_list, parent, false)
), QuestionsListAdapter.OnQuestionClickListener, QuestionsListViewMvc {

	private val mLstQuestions: RecyclerView = findViewById(R.id.lst_questions)
	private val progressBar: ProgressBar = findViewById(R.id.progress_bar)
	private val toolbar: Toolbar = findViewById(R.id.toolbar)
	private val toolbarViewMvc: ToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(toolbar).also {
		it.setTitle(toolbar.resources.getString(R.string.questions_list_screen_title))
		it.enableHamburgerButtonAndListen(object : ToolbarViewMvc.HamburgerClickListener {
			override fun onHamburgerButtonClicked() {
				openDrawerLayout()
			}
		})
		toolbar.addView(it.rootView)
	}

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

	override fun onDrawerItemClicked(questionsList: DrawerItem) {
		when (questionsList) {
			DrawerItem.QUESTIONS_LIST -> {
				getListeners().forEach {
					it.onQuestionsClicked()
				}
			}
		}
	}
}