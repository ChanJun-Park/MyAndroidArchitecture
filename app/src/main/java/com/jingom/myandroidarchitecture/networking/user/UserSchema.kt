package com.jingom.myandroidarchitecture.networking.user

import com.google.gson.annotations.SerializedName

data class UserSchema(
	@field:SerializedName("display_name") val userDisplayName: String,
	@field:SerializedName("profile_image") val userAvatarUrl: String
)