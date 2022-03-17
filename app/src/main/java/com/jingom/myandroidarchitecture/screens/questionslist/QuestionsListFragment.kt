package com.jingom.myandroidarchitecture.screens.questionslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jingom.myandroidarchitecture.screens.common.ViewMvcFactory
import com.jingom.myandroidarchitecture.screens.common.dialogs.DialogsEventBus
import com.jingom.myandroidarchitecture.screens.common.dialogs.promptdialogs.PromptDialogEvent
import com.jingom.myandroidarchitecture.screens.questiondetails.QuestionDetailsFragment
import com.jingom.myandroidarchitecture.screens.questionslist.QuestionsListController.Companion.SAVED_STATE_SCREEN_STATE
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class QuestionsListFragment: Fragment() {

	companion object {
		fun newInstance() = QuestionsListFragment()
	}

	@Inject lateinit var viewMvcFactory: ViewMvcFactory
	@Inject lateinit var questionsListController: QuestionsListController

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		val questionsListViewMvc = viewMvcFactory.getQuestionListViewMvc(container)

		savedInstanceState?.let {
			val savedState = it.getSerializable(SAVED_STATE_SCREEN_STATE) as QuestionsListController.SavedState
			questionsListController.restoreSavedState(savedState)
		}

		questionsListController.bindView(questionsListViewMvc)

		return questionsListViewMvc.rootView
	}

	override fun onStart() {
		super.onStart()
		questionsListController.onStart()
	}

	override fun onStop() {
		super.onStop()
		questionsListController.onStop()
	}

	override fun onSaveInstanceState(outState: Bundle) {
		super.onSaveInstanceState(outState)

		val savedState = questionsListController.getSavedState()
		outState.putSerializable(SAVED_STATE_SCREEN_STATE, savedState)
	}
}