package com.jingom.myandroidarchitecture.screens.common.dialogs

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentManager
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.screens.common.dialogs.infodialogs.InfoDialog
import com.jingom.myandroidarchitecture.screens.common.dialogs.promptdialogs.PromptDialog
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class DialogsManager @Inject constructor(
	private val fragmentManager: FragmentManager,
	@ActivityContext private val context: Context
) {
	fun showUseCaseErrorDialog(tag: String?) {
		val infoDialog = PromptDialog.newPromptDialog(
			getString(R.string.error_network_call_failed_title),
			getString(R.string.error_network_call_failed_message),
			getString(R.string.error_network_call_failed_positive_button_caption),
			getString(R.string.error_network_call_failed_negative_button_caption)
		)
		infoDialog.show(fragmentManager, tag)
	}

	fun getShownDialogTag(): String? {
		fragmentManager.fragments.forEach { fragment ->
			if (fragment is BaseDialog) {
				return fragment.tag
			}
		}

		return null
	}

	private fun getString(@StringRes id: Int): String {
		return context.getString(id)
	}
}