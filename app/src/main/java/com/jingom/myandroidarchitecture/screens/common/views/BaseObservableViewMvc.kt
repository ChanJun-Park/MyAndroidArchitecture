package com.jingom.myandroidarchitecture.screens.common.views

import android.view.View
import java.util.*

abstract class BaseObservableViewMvc<ListenerType>(
	override val rootView: View
): BaseViewMvc(rootView), ObservableViewMvc<ListenerType> {
	private val listeners = HashSet<ListenerType>()

	protected fun getListeners() = Collections.unmodifiableSet(listeners)

	override fun registerListener(listener: ListenerType) {
		listeners.add(listener)
	}

	override fun unregisterListener(listener: ListenerType) {
		listeners.remove(listener)
	}
}