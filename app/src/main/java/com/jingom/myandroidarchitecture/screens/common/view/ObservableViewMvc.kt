package com.jingom.myandroidarchitecture.screens.common.view

interface ObservableViewMvc<ListenerType>: ViewMvc {
	fun registerListener(listener: ListenerType)
	fun unregisterListener(listener: ListenerType)
}