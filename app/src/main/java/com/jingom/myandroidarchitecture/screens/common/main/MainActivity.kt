package com.jingom.myandroidarchitecture.screens.common.main

import android.os.Bundle
import android.widget.FrameLayout
import com.jingom.myandroidarchitecture.screens.common.ViewMvcFactory
import com.jingom.myandroidarchitecture.screens.common.controllers.BackPressDispatcher
import com.jingom.myandroidarchitecture.screens.common.controllers.BackPressedListener
import com.jingom.myandroidarchitecture.screens.common.controllers.BaseActivity
import com.jingom.myandroidarchitecture.screens.common.controllers.FragmentFrameWrapper
import com.jingom.myandroidarchitecture.screens.common.navdrawer.NavDrawerHelper
import com.jingom.myandroidarchitecture.screens.common.navdrawer.NavDrawerViewMvc
import com.jingom.myandroidarchitecture.screens.common.screensnavigator.ScreensNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity(), BackPressDispatcher, FragmentFrameWrapper, NavDrawerHelper, NavDrawerViewMvc.Listener {

	@Inject lateinit var viewMvcFactory: ViewMvcFactory
	@Inject lateinit var screensNavigator: ScreensNavigator

	private val backPressedListeners = HashSet<BackPressedListener>()
	private lateinit var navDrawerViewMvc: NavDrawerViewMvc

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		navDrawerViewMvc = viewMvcFactory.getNavDrawerViewMvc(null)

		setContentView(navDrawerViewMvc.rootView)

		if (savedInstanceState == null) {
			screensNavigator.toQuestionsList()
		}
	}

	override fun onStart() {
		super.onStart()
		navDrawerViewMvc.registerListener(this)
	}

	override fun onStop() {
		super.onStop()
		navDrawerViewMvc.unregisterListener(this)
	}

	override fun onBackPressed() {
		var isBackPressConsumedByAnyListener = false
		backPressedListeners.forEach {
			if (it.onBackPressed()) {
				isBackPressConsumedByAnyListener = true
			}
		}
		if (!isBackPressConsumedByAnyListener) {
			super.onBackPressed()
		}
	}

	override fun registerListener(listener: BackPressedListener) {
		backPressedListeners.add(listener)
	}

	override fun unregisterListener(listener: BackPressedListener) {
		backPressedListeners.remove(listener)
	}

	override fun getFragmentFrame(): FrameLayout {
		return navDrawerViewMvc.frameLayout
	}

	override fun openDrawer() {
		return navDrawerViewMvc.openDrawer()
	}

	override fun closeDrawer() {
		return navDrawerViewMvc.closeDrawer()
	}

	override fun isDrawerOpen(): Boolean {
		return navDrawerViewMvc.isDrawerOpen()
	}

	override fun onQuestionListItemClicked() {
		screensNavigator.toQuestionsList()
	}
}