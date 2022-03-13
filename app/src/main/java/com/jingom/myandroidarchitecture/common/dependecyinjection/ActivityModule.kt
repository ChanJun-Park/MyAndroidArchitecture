package com.jingom.myandroidarchitecture.common.dependecyinjection

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
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
	@ActivityContextQualifier
	fun context(activity: Activity): Context = activity
}