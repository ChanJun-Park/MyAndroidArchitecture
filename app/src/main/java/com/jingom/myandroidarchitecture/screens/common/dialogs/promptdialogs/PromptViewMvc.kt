package com.jingom.myandroidarchitecture.screens.common.dialogs.promptdialogs

import com.jingom.myandroidarchitecture.screens.common.views.ObservableViewMvc

interface PromptViewMvc: ObservableViewMvc<PromptViewMvc.Listener> {

	interface Listener {
		fun onPositiveButtonClicked()
		fun onNegativeButtonClicked()
	}

	fun setTitle(title: String?)
	fun setMessage(message: String?)
	fun setPositiveButtonCaption(caption: String?)
	fun setNegativeButtonCaption(caption: String?)
}