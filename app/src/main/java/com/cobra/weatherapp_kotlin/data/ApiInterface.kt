package com.cobra.weatherapp_kotlin.data
import com.cobra.weatherapp_kotlin.data.models.CurrentWeather
//import com.google.android.gms.common.api.Response
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("weather?")
    //the suspend function from courtines
    suspend fun  getCurrentWeather(
        //we have ot pass the string querises to save the data
        @Query("q") city : String ,
        @Query("units") units : String ,
        @Query("appid") apiKey : String ,
    ):Response<CurrentWeather>

    //ممكن استخدم ال call back function
    //ولاكن ال courtine احسن انى استخدمل ال Response وبتكون suspend function
    //Response<CurrentWeather     بتجيب كل اليوزر الى عندى
}