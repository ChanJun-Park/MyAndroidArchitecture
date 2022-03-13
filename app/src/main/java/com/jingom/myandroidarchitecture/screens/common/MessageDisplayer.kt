package com.jingom.myandroidarchitecture.screens.common

import android.content.Context
import android.widget.Toast
import com.jingom.myandroidarchitecture.R
import com.jingom.myandroidarchitecture.common.dependecyinjection.ActivityContextQualifier
import javax.inject.Inject

class MessageDisplayer @Inject constructor(
	@ActivityContextQualifier private val context: Context
){

	fun showFetchError() {
		Toast.makeText(context, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show()
	}
}