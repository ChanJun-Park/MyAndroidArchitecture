package com.jingom.myandroidarchitecture.screens.common.dialogs.infodialogs

import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.screens.common.dialogs.BaseDialog


open class InfoDialog: BaseDialog() {

	companion object {
		private const val ARG_TITLE = "ARG_TITLE"
		private const val ARG_MESSAGE = "ARG_MESSAGE"
		private const val ARG_BUTTON_CAPTION = "ARG_BUTTON_CAPTION"

		fun newInfoDialog(title: String, message: String, buttonCaption: String): InfoDialog {
			val infoDialog = InfoDialog()
			val args = Bundle(3).apply {
				putString(ARG_TITLE, title)
				putString(ARG_MESSAGE, message)
				putString(ARG_BUTTON_CAPTION, buttonCaption)
			}
			infoDialog.arguments = args
			return infoDialog
		}
	}


	private lateinit var mTxtTitle: TextView
	private lateinit var mTxtMessage: TextView
	private lateinit var mBtnPositive: AppCompatButton

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		val dialog = Dialog(requireContext())
		dialog.setContentView(R.layout.dialog_info)

		mTxtTitle = dialog.findViewById(R.id.txt_title)
		mTxtMessage = dialog.findViewById(R.id.txt_message)
		mBtnPositive = dialog.findViewById(R.id.btn_positive)

		mTxtTitle.text = requireArguments().getString(ARG_TITLE)
		mTxtMessage.text = requireArguments().getString(ARG_MESSAGE)
		mBtnPositive.text = requireArguments().getString(ARG_BUTTON_CAPTION)
		mBtnPositive.setOnClickListener {
			onButtonClicked()
		}
		return dialog
	}

	private fun onButtonClicked() {
		dismiss()
	}
}