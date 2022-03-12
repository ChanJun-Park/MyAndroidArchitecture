package com.jingom.myandroidarchitecture.common.dependecyinjection

import com.jingom.myandroidarchitecture.common.Constants
import com.jingom.myandroidarchitecture.networking.StackoverflowApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

	@Provides
	@AppScope
	fun retrofit(): Retrofit = Retrofit.Builder()
		.baseUrl(Constants.BASE_URL)
		.addConverterFactory(GsonConverterFactory.create())
		.build()

	@Provides
	@AppScope
	fun stackoverflowApi(retrofit: Retrofit): StackoverflowApi = retrofit.create(StackoverflowApi::class.java)
}