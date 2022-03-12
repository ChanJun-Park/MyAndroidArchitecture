package com.jingom.myandroidarchitecture.screens.questionslist

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.common.Constants
import com.jingom.myandroidarchitecture.networking.QuestionSchema
import com.jingom.myandroidarchitecture.networking.QuestionsListResponseSchema
import com.jingom.myandroidarchitecture.networking.StackoverflowApi
import com.jingom.myandroidarchitecture.questions.Question
import com.jingom.myandroidarchitecture.screens.common.BaseActivity
import com.jingom.myandroidarchitecture.screens.questionslist.QuestionsListAdapter.OnQuestionClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuestionsListActivity : BaseActivity(), OnQuestionClickListener {

	private lateinit var mStackoverflowApi: StackoverflowApi
	private lateinit var mLstQuestions: ListView
	private lateinit var mQuestionsListAdapter: QuestionsListAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.layout_questions_list)

		mLstQuestions = findViewById(R.id.lst_questions)
		mQuestionsListAdapter = QuestionsListAdapter(this, this)
		mLstQuestions.adapter = mQuestionsListAdapter
		mStackoverflowApi = Retrofit.Builder()
			.baseUrl(Constants.BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
			.create(StackoverflowApi::class.java)
	}

	override fun onStart() {
		super.onStart()
		fetchQuestions()
	}

	private fun fetchQuestions() {
		mStackoverflowApi.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
			.enqueue(object : Callback<QuestionsListResponseSchema?> {
				override fun onResponse(call: Call<QuestionsListResponseSchema?>, response: Response<QuestionsListResponseSchema?>) {
					if (response.isSuccessful) {
						bindQuestions(response.body()!!.questions)
					} else {
						networkCallFailed()
					}
				}

				override fun onFailure(call: Call<QuestionsListResponseSchema?>, t: Throwable) {
					networkCallFailed()
				}

			})
	}

	private fun bindQuestions(questionSchemas: List<QuestionSchema>) {
		val questions: MutableList<Question> = ArrayList(questionSchemas.size)
		for ((title, id) in questionSchemas) {
			questions.add(Question(id, title))
		}
		mQuestionsListAdapter.clear()
		mQuestionsListAdapter.addAll(questions)
		mQuestionsListAdapter.notifyDataSetChanged()
	}

	private fun networkCallFailed() {
		Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show()
	}

	override fun onQuestionClicked(question: Question) {
		Toast.makeText(this, question.title, Toast.LENGTH_SHORT).show()
	}
}