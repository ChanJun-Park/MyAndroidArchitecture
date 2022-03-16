package com.jingom.myandroidarchitecture.screens.common.dialogs.promptdialogs

import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.screens.common.dialogs.BaseDialog
import com.jingom.myandroidarchitecture.screens.common.dialogs.DialogsEventBus
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PromptDialog : BaseDialog() {
	private lateinit var mTxtTitle: TextView
	private lateinit var mTxtMessage: TextView
	private lateinit var mBtnPositive: AppCompatButton
	private lateinit var mBtnNegative: AppCompatButton

	@Inject lateinit var mDialogsEventBus: DialogsEventBus

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		checkNotNull(arguments) { "arguments mustn't be null" }
		val dialog = Dialog(requireContext())
		dialog.setContentView(R.layout.dialog_prompt)

		mTxtTitle = dialog.findViewById(R.id.txt_title)
		mTxtMessage = dialog.findViewById(R.id.txt_message)
		mBtnPositive = dialog.findViewById(R.id.btn_positive)
		mBtnNegative = dialog.findViewById(R.id.btn_negative)

		mTxtTitle.text = requireArguments().getString(ARG_TITLE)
		mTxtMessage.text = requireArguments().getString(ARG_MESSAGE)
		mBtnPositive.text = requireArguments().getString(ARG_POSITIVE_BUTTON_CAPTION)
		mBtnNegative.text = requireArguments().getString(ARG_NEGATIVE_BUTTON_CAPTION)

		mBtnPositive.setOnClickListener{
			onPositiveButtonClicked()
		}
		mBtnNegative.setOnClickListener{
			onNegativeButtonClicked()
		}
		return dialog
	}

	private fun onPositiveButtonClicked() {
		dismiss()
		mDialogsEventBus.postEvent(PromptDialogEvent(PromptDialogEvent.Button.POSITIVE))
	}

	private fun onNegativeButtonClicked() {
		dismiss()
		mDialogsEventBus.postEvent(PromptDialogEvent(PromptDialogEvent.Button.NEGATIVE))
	}

	companion object {
		private const val ARG_TITLE = "ARG_TITLE"
		private const val ARG_MESSAGE = "ARG_MESSAGE"
		private const val ARG_POSITIVE_BUTTON_CAPTION = "ARG_POSITIVE_BUTTON_CAPTION"
		private const val ARG_NEGATIVE_BUTTON_CAPTION = "ARG_NEGATIVE_BUTTON_CAPTION"

		fun newPromptDialog(
			title: String,
			message: String,
			positiveButtonCaption: String,
			negativeButtonCaption: String
		): PromptDialog {

			val promptDialog = PromptDialog()
			val args = Bundle(4).apply {
				putString(ARG_TITLE, title)
				putString(ARG_MESSAGE, message)
				putString(ARG_POSITIVE_BUTTON_CAPTION, positiveButtonCaption)
				putString(ARG_NEGATIVE_BUTTON_CAPTION, negativeButtonCaption)
			}

			promptDialog.arguments = args
			return promptDialog
		}
	}
}
