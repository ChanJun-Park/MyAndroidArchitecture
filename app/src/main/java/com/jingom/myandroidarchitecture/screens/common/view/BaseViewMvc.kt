package com.jingom.myandroidarchitecture.screens.common.view

import android.content.Context
import android.view.View
import androidx.annotation.IdRes

abstract class BaseViewMvc(
	override val rootView: View
): ViewMvc {
	protected fun <T : View?> findViewById(@IdRes id: Int): T {
		return rootView.findViewById<T>(id)
	}

	protected val context: Context get() = rootView.context
}