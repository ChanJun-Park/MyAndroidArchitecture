package com.jingom.myandroidarchitecture.screens.common.navdrawer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.screens.common.views.BaseObservableViewMvc


class NavDrawerViewMvcImpl(
	layoutInflater: LayoutInflater,
	parent: ViewGroup?,
): BaseObservableViewMvc<NavDrawerViewMvc.Listener>(
	layoutInflater.inflate(R.layout.layout_drawer, parent, false)
), NavDrawerViewMvc {
	override val frameLayout: FrameLayout = findViewById(R.id.frame_content)

	private val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
	private val navigationView: NavigationView = findViewById<NavigationView>(R.id.nav_view).apply {
		setNavigationItemSelectedListener { item ->
			drawerLayout.closeDrawers()
			if (item.itemId == R.id.drawer_menu_questions_list) {
				getListeners().forEach {
					it.onQuestionListItemClicked()
				}
			}
			false
		}
	}

	override fun isDrawerOpen(): Boolean {
		return drawerLayout.isDrawerOpen(GravityCompat.START)
	}

	override fun openDrawer() {
		drawerLayout.openDrawer(GravityCompat.START)
	}

	override fun closeDrawer() {
		drawerLayout.closeDrawers()
	}
}