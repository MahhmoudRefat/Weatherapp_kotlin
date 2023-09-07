package com.cobra.weatherapp_kotlin.data

import retrofit2.http.GET

interface ApiInterface {
    @GET("weather?")
    suspend fun  getCurrentWeather()
}