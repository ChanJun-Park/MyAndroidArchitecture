package com.jingom.myandroidarchitecture.screens.common.dialogs

import com.jingom.myandroidarchitecture.common.BaseObservable
import com.jingom.myandroidarchitecture.common.dependecyinjection.AppScope
import javax.inject.Inject

@AppScope
class DialogsEventBus @Inject constructor(): BaseObservable<DialogsEventBus.Listener>() {
	interface Listener {
		fun onDialogEvent(event: Any)
	}

	fun postEvent(event: Any) {
		listeners.forEach {
			it.onDialogEvent(event)
		}
	}
}