package com.jingom.myandroidarchitecture.screens.common.dialogs.promptdialogs

import android.app.Dialog
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.screens.common.ViewMvcFactory
import com.jingom.myandroidarchitecture.screens.common.dialogs.BaseDialog
import com.jingom.myandroidarchitecture.screens.common.dialogs.DialogsEventBus
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PromptDialog : BaseDialog(), PromptViewMvc.Listener {

	@Inject lateinit var mDialogsEventBus: DialogsEventBus
	@Inject lateinit var viewMvcFactory: ViewMvcFactory
	private lateinit var viewMvc: PromptViewMvc

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		checkNotNull(arguments) { "arguments mustn't be null" }
		viewMvc = viewMvcFactory.getPromptViewMvc()

		val dialog = Dialog(requireContext())
		dialog.setContentView(viewMvc.rootView)

		val args = requireArguments()

		viewMvc.run {
			setTitle(args.getString(ARG_TITLE))
			setMessage(args.getString(ARG_MESSAGE))
			setPositiveButtonCaption(args.getString(ARG_POSITIVE_BUTTON_CAPTION))
			setNegativeButtonCaption(args.getString(ARG_NEGATIVE_BUTTON_CAPTION))
		}

		return dialog

	}

	override fun onStart() {
		super.onStart()
		viewMvc.registerListener(this)
	}

	override fun onStop() {
		super.onStop()
		viewMvc.unregisterListener(this)
	}

	override fun onPositiveButtonClicked() {
		dismiss()
		mDialogsEventBus.postEvent(PromptDialogEvent(PromptDialogEvent.Button.POSITIVE))
	}

	override fun onNegativeButtonClicked() {
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
