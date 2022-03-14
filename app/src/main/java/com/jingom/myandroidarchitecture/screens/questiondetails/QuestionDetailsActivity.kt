package com.jingom.myandroidarchitecture.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.questions.FetchQuestionDetailsUseCase
import com.jingom.myandroidarchitecture.screens.common.controllers.BackPressedListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class QuestionDetailsActivity : AppCompatActivity() {

	companion object {
		private const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"

		fun start(context: Context, questionId: String) {
			Intent(context, QuestionDetailsActivity::class.java).also {
				it.putExtra(EXTRA_QUESTION_ID, questionId)
				context.startActivity(it)
			}
		}
	}

	private lateinit var backPressedListener: BackPressedListener

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.layout_content_frame)

		val questionId = intent.getStringExtra(EXTRA_QUESTION_ID)
		if (questionId == null) {
			finish()
			return
		}

		val fragment: QuestionDetailsFragment

		if (savedInstanceState == null) {
			fragment = QuestionDetailsFragment.newFragment(questionId)
			supportFragmentManager.beginTransaction().add(
				R.id.frame_content, fragment
			).commit()
		} else {
			fragment = supportFragmentManager.findFragmentById(R.id.frame_content) as QuestionDetailsFragment
		}

		backPressedListener = fragment
	}

	override fun onBackPressed() {
		if (!backPressedListener.onBackPressed()) {
			super.onBackPressed()
		}
	}
}