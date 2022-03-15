package com.jingom.myandroidarchitecture.screens.common.navdrawer

import android.widget.FrameLayout
import com.jingom.myandroidarchitecture.screens.common.views.ObservableViewMvc

interface NavDrawerViewMvc: ObservableViewMvc<NavDrawerViewMvc.Listener> {

	interface Listener {
		fun onQuestionListItemClicked()
	}

	fun isDrawerOpen(): Boolean
	fun openDrawer()
	fun closeDrawer()

	val frameLayout: FrameLayout
}