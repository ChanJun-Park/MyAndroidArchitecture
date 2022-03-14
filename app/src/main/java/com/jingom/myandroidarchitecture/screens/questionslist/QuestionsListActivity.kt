package com.jingom.myandroidarchitecture.screens.questionslist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.screens.common.controllers.BackPressedListener
import com.jingom.myandroidarchitecture.screens.common.controllers.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionsListActivity : BaseActivity() {

	companion object {
		fun startAndClearTop(context: Context) {
			Intent(context, QuestionsListActivity::class.java).let {
				it.flags = it.flags or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
				context.startActivity(it)
			}
		}
	}

	private lateinit var backPressedListener: BackPressedListener

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.layout_content_frame)

		val fragment: QuestionsListFragment
		if (savedInstanceState == null) {
			fragment = QuestionsListFragment()

			supportFragmentManager.beginTransaction().add(
				R.id.frame_content, fragment
			).commit()
		} else {
			fragment = supportFragmentManager.findFragmentById(R.id.frame_content) as QuestionsListFragment
		}

		backPressedListener = fragment
	}

	override fun onBackPressed() {
		if (!backPressedListener.onBackPressed()) {
			super.onBackPressed()
		}
	}
}