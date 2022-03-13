package com.jingom.myandroidarchitecture.screens.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.screens.common.view.BaseObservableViewMvc

class ToolbarViewMvc(
	layoutInflater: LayoutInflater,
	parent: ViewGroup
): BaseObservableViewMvc<ToolbarViewMvc.Listener>(
	layoutInflater.inflate(R.layout.layout_toolbar, parent, false)
) {

	interface Listener {
		fun onNavigationUpClicked()

		fun onHamburgerButtonClicked()
	}

	private val title: TextView = findViewById(R.id.title)
	private val upButton: ImageButton = findViewById<ImageButton>(R.id.up_button).apply {
		setOnClickListener {
			getListeners().forEach { listener ->
				listener.onNavigationUpClicked()
			}
		}
	}
	private val hamburgerButton: ImageButton = findViewById<ImageButton>(R.id.hamburger_button).apply {
		setOnClickListener {
			getListeners().forEach { listener ->
				listener.onHamburgerButtonClicked()
			}
		}
	}

	fun setTitle(title: String) {
		this.title.text = title
	}

	fun showUpButton() {
		upButton.visibility = View.VISIBLE
	}

	fun hideUpButton() {
		upButton.visibility = View.GONE
	}

	fun showHamburgerButton() {
		hamburgerButton.visibility = View.VISIBLE
	}

	fun hideHamburgerButton() {
		hamburgerButton.visibility = View.GONE
	}
}