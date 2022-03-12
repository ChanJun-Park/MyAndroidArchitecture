package com.jingom.myandroidarchitecture.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.networking.QuestionDetailsResponseSchema
import com.jingom.myandroidarchitecture.networking.QuestionSchema
import com.jingom.myandroidarchitecture.networking.StackoverflowApi
import com.jingom.myandroidarchitecture.questions.QuestionDetails
import com.jingom.myandroidarchitecture.screens.common.ViewMvcFactory
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class QuestionDetailsActivity : AppCompatActivity() {

	@Inject lateinit var stackoverflowApi: StackoverflowApi
	@Inject lateinit var viewMvcFactory: ViewMvcFactory
	private lateinit var questionDetailsViewMvc: QuestionDetailsViewMvc
	private var questionId: String? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		questionId = intent.getStringExtra(EXTRA_QUESTION_ID)
		questionDetailsViewMvc = viewMvcFactory.getQuestionDetailsViewMvc(null)

		setContentView(questionDetailsViewMvc.rootView)
	}

	override fun onStart() {
		super.onStart()
		fetchQuestionDetails()
	}

	private fun fetchQuestionDetails() {
		questionId?.let {

			questionDetailsViewMvc.showProgressIndication()

			stackoverflowApi.fetchQuestionDetails(it).enqueue(object : Callback<QuestionDetailsResponseSchema?> {
				override fun onResponse(call: Call<QuestionDetailsResponseSchema?>, response: Response<QuestionDetailsResponseSchema?>) {
					val questionSchema = response.body()?.question

					if (response.isSuccessful && questionSchema != null) {
						bindQuestionDetails(questionSchema)
					} else {
						networkCallFailed()
					}
				}

				override fun onFailure(call: Call<QuestionDetailsResponseSchema?>, t: Throwable) {
					networkCallFailed()
				}

			})
		}
	}

	private fun bindQuestionDetails(questionSchema: QuestionSchema) {
		val questionDetails = QuestionDetails(questionSchema.id, questionSchema.title, questionSchema.body)

		questionDetailsViewMvc.apply {
			hideProgressIndication()
			bindQuestionDetails(questionDetails)
		}
	}

	private fun networkCallFailed() {
		questionDetailsViewMvc.hideProgressIndication()
		Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show()
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