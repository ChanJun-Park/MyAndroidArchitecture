package com.jingom.myandroidarchitecture.common

import java.util.*
import java.util.concurrent.ConcurrentHashMap

abstract class BaseObservable<LISTENER_CLASS> {
	// thread-safe set of listeners
	private val mListeners: MutableSet<LISTENER_CLASS> = Collections.newSetFromMap(
		ConcurrentHashMap<LISTENER_CLASS, Boolean>(1)
	)

	fun registerListener(listener: LISTENER_CLASS) {
		mListeners.add(listener)
	}

	fun unregisterListener(listener: LISTENER_CLASS) {
		mListeners.remove(listener)
	}

	protected val listeners: Set<LISTENER_CLASS>
		get() = Collections.unmodifiableSet(mListeners)
}
