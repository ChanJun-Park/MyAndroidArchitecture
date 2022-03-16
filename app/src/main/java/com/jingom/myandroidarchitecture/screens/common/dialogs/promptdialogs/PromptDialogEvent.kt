package com.jingom.myandroidarchitecture.screens.common.dialogs.promptdialogs

data class PromptDialogEvent(val clickedButton: Button) {
	enum class Button {
		POSITIVE, NEGATIVE
	}
}
