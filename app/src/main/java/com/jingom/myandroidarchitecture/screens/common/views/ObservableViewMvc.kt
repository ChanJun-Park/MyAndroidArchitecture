package com.jingom.myandroidarchitecture.screens.common.views

interface ObservableViewMvc<ListenerType>: ViewMvc {
	fun registerListener(listener: ListenerType)
	fun unregisterListener(listener: ListenerType)
}