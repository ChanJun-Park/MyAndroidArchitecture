package com.jingom.myandroidarchitecture.screens.common.main

import android.os.Bundle
import android.widget.FrameLayout
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.screens.common.controllers.BackPressDispatcher
import com.jingom.myandroidarchitecture.screens.common.controllers.BackPressedListener
import com.jingom.myandroidarchitecture.screens.common.controllers.BaseActivity
import com.jingom.myandroidarchitecture.screens.common.controllers.FragmentFrameWrapper
import com.jingom.myandroidarchitecture.screens.questionslist.QuestionsListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity(), BackPressDispatcher, FragmentFrameWrapper {

	private val backPressedListeners = HashSet<BackPressedListener>()

	private lateinit var frameLayout: FrameLayout

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.layout_content_frame)

		val fragment: QuestionsListFragment
		if (savedInstanceState == null) {
			fragment = QuestionsListFragment()

			supportFragmentManager.beginTransaction().add(
				R.id.frame_content, fragment
			).commit()
		}

		frameLayout = findViewById(R.id.frame_content)
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
		return frameLayout
	}
}