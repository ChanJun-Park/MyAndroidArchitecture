package com.jingom.myandroidarchitecture.screens.common.toolbar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.screens.common.views.BaseViewMvc

class ToolbarViewMvc(
	layoutInflater: LayoutInflater,
	parent: ViewGroup
): BaseViewMvc(
	layoutInflater.inflate(R.layout.layout_toolbar, parent, false)
) {

	interface NavigationUpClickListener {
		fun onNavigationUpClicked()
	}

	interface HamburgerClickListener {
		fun onHamburgerButtonClicked()
	}

	private var navigationUpClickListener: NavigationUpClickListener? = null
	private var hamburgerClickListener: HamburgerClickListener? = null

	private val title: TextView = findViewById(R.id.title)
	private val upButton: ImageButton = findViewById<ImageButton>(R.id.up_button).apply {
		setOnClickListener {
			navigationUpClickListener?.onNavigationUpClicked()
		}
	}
	private val hamburgerButton: ImageButton = findViewById<ImageButton>(R.id.hamburger_button).apply {
		setOnClickListener {
			hamburgerClickListener?.onHamburgerButtonClicked()
		}
	}

	fun setTitle(title: String) {
		this.title.text = title
	}

	fun enableNavigationUpButtonAndListen(listener: NavigationUpClickListener) {
		if (hamburgerClickListener != null) {
			throw RuntimeException("hamburger and up shouldn't be shown together");
		}
		navigationUpClickListener = listener
		upButton.visibility = View.VISIBLE
	}

	fun enableHamburgerButtonAndListen(listener: HamburgerClickListener) {
		if (navigationUpClickListener != null) {
			throw RuntimeException("hamburger and up shouldn't be shown together");
		}
		hamburgerClickListener = listener
		hamburgerButton.visibility = View.VISIBLE
	}
}