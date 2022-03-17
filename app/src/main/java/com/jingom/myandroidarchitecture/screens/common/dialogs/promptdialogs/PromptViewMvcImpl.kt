package com.jingom.myandroidarchitecture.screens.common.dialogs.promptdialogs

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.screens.common.views.BaseObservableViewMvc

class PromptViewMvcImpl(
	layoutInflater: LayoutInflater,
	parent: ViewGroup?
): BaseObservableViewMvc<PromptViewMvc.Listener>(
	layoutInflater.inflate(R.layout.dialog_prompt, parent, false)
), PromptViewMvc {

	private val mTxtTitle: TextView = findViewById(R.id.txt_title)
	private val mTxtMessage: TextView = findViewById(R.id.txt_message)
	private val mBtnPositive: AppCompatButton = findViewById<AppCompatButton>(R.id.btn_positive).apply {
		setOnClickListener {
			getListeners().forEach {
				it.onPositiveButtonClicked()
			}
		}
	}
	private val mBtnNegative: AppCompatButton = findViewById<AppCompatButton>(R.id.btn_negative).apply {
		setOnClickListener {
			getListeners().forEach {
				it.onNegativeButtonClicked()
			}
		}
	}

	override fun setTitle(title: String?) {
		mTxtTitle.text = title
	}

	override fun setMessage(message: String?) {
		mTxtMessage.text = message
	}

	override fun setPositiveButtonCaption(caption: String?) {
		mBtnPositive.text = caption
	}

	override fun setNegativeButtonCaption(caption: String?) {
		mBtnNegative.text = caption
	}

}