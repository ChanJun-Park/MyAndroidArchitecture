package com.jingom.myandroidarchitecture.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jingom.myandroidarchitecture.R

class QuestionDetailsActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_question_details)
	}

	companion object {
		private const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"

		fun start(context: Context, questionId: String) {
			Intent(context, QuestionDetailsActivity::class.java).also {
				it.putExtra(EXTRA_QUESTION_ID, questionId)
				context.startActivity(it)
			}
		}
	}
}