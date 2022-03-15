package com.jingom.myandroidarchitecture.common.dependecyinjection

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.jingom.myandroidarchitecture.screens.common.controllers.BackPressDispatcher
import com.jingom.myandroidarchitecture.screens.common.controllers.FragmentFrameWrapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

	@Provides
	@ActivityScoped
	fun layoutInflater(activity: Activity): LayoutInflater = LayoutInflater.from(activity)

	@Provides
	fun fragmentManager(activity: FragmentActivity): FragmentManager = activity.supportFragmentManager

	@Provides
	fun fragmentFrameWrapper(activity: Activity): FragmentFrameWrapper = activity as FragmentFrameWrapper

	@Provides
	fun backPressDispatcher(activity: Activity): BackPressDispatcher = activity as BackPressDispatcher
}