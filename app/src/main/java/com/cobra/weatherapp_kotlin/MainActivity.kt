package com.cobra.weatherapp_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.cobra.weatherapp_kotlin.databinding.ActivityMainBinding
import com.cobra.weatherapp_kotlin.utils.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // البايندينج عشان مكتبش ال find view by id
        //getCurrentWeather()
    }
    //this function to pass data to the retrofit instance
    private fun getCurrentWeather() {
        //Dispatchers.IO عشان هتعاملم ع حاجة جاية من ع الانتر نت
        GlobalScope.launch ( Dispatchers.IO ){
            val response = try {
                //we will pass the quieries
                RetrofitInstance.api.getCurrentWeather("egypt","metric",applicationContext.getString((R.string.apiKey)))

            } catch (e: IOException) {
                //application error
                Toast.makeText(applicationContext,"app error${e.message} " ,Toast.LENGTH_SHORT).show()
                return@launch
            } catch (e: HttpException) {
            //htpp errors
                Toast.makeText(applicationContext,"http error${e.message} " ,Toast.LENGTH_SHORT).show()
            return@launch
            }
            if (response.isSuccessful && response.body() != null )
            {
                withContext(Dispatchers.Main){
                    binding.tvTemp.text = "temperature : ${response.body()!!.main.temp}"
                }
            }
        }
    }

}


