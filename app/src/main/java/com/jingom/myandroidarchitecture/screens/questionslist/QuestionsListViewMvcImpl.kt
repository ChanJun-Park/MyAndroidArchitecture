package com.jingom.myandroidarchitecture.screens.questionslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.questions.Question
import com.jingom.myandroidarchitecture.screens.common.toolbar.ToolbarViewMvc
import com.jingom.myandroidarchitecture.screens.common.ViewMvcFactory
import com.jingom.myandroidarchitecture.screens.common.navdrawer.NavDrawerHelper
import com.jingom.myandroidarchitecture.screens.common.views.BaseObservableViewMvc

class QuestionsListViewMvcImpl(
	layoutInflater: LayoutInflater,
	parent: ViewGroup?,
	viewMvcFactory: ViewMvcFactory,
	private val navDrawerHelper: NavDrawerHelper
) : BaseObservableViewMvc<QuestionsListViewMvc.Listener>(
	layoutInflater.inflate(R.layout.layout_questions_list, parent, false)
), QuestionsListAdapter.OnQuestionClickListener, QuestionsListViewMvc {

	private val mLstQuestions: RecyclerView = findViewById(R.id.lst_questions)
	private val progressBar: ProgressBar = findViewById(R.id.progress_bar)
	private val toolbar: Toolbar = findViewById(R.id.toolbar)
	private val toolbarViewMvc: ToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(toolbar).also {
		it.setTitle(toolbar.resources.getString(R.string.questions_list_screen_title))
		it.enableHamburgerButtonAndListen(object : ToolbarViewMvc.HamburgerClickListener {
			override fun onHamburgerButtonClicked() {
				navDrawerHelper.openDrawer()
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
}