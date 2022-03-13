package com.jingom.myandroidarchitecture.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.screens.common.view.BaseViewMvc

class ToolbarViewMvc(
	layoutInflater: LayoutInflater,
	parent: ViewGroup
): BaseViewMvc(
	layoutInflater.inflate(R.layout.layout_toolbar, parent, false)
) {
	private val title: TextView = findViewById(R.id.title)

	fun setTitle(title: String) {
		this.title.text = title
	}
}