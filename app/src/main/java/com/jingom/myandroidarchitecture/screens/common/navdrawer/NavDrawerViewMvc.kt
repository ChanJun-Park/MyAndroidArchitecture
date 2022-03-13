package com.jingom.myandroidarchitecture.screens.common.navdrawer

interface NavDrawerViewMvc {
	fun isDrawerOpen(): Boolean
	fun openDrawer()
	fun closeDrawer()
}