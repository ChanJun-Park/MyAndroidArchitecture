package com.jingom.myandroidarchitecture.screens.common.dialogs

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentManager
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.screens.common.dialogs.infodialogs.InfoDialog
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class DialogsManager @Inject constructor(
	private val fragmentManager: FragmentManager,
	@ActivityContext private val context: Context
) {
	fun showUseCaseErrorDialog(tag: String?) {
		val infoDialog = InfoDialog.newInfoDialog(
			getString(R.string.error_network_call_failed_title),
			getString(R.string.error_network_call_failed_message),
			getString(R.string.error_network_call_failed_button_caption)
		)
		infoDialog.show(fragmentManager, tag)
	}

	private fun getString(@StringRes id: Int): String {
		return context.getString(id)
	}
}