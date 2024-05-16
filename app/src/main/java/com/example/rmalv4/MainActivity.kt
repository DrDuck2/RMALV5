package com.example.rmalv4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rmalv4.model.Weather
import com.example.rmalv4.view.BMICalculatorScreen
import com.example.rmalv4.view.BackgroundImage
import com.example.rmalv4.view.LoginRegisterScreen
import com.example.rmalv4.view.StepCounterScreen
import com.example.rmalv4.view.WeatherScreen
import com.example.rmalv4.viewmodel.BMIViewModel
import com.example.rmalv4.viewmodel.LoginRegisterViewModel
import com.example.rmalv4.viewmodel.StepViewModel
import com.example.rmalv4.viewmodel.WeatherViewModel


class MainActivity : ComponentActivity() {
    private lateinit var stepViewModel: StepViewModel
    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stepViewModel = ViewModelProvider(this)[StepViewModel::class.java]
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "BMICalculatorScreen" ){
                composable("LoginRegisterScreen"){
                    LoginRegisterScreen(navController, LoginRegisterViewModel())
                }
                composable("BMICalculatorScreen"){
                    BMICalculatorScreen(navController , BMIViewModel())
                }
                composable("StepCounterScreen"){
                    stepViewModel.registerSensor()
                    StepCounterScreen(navController, viewModel = stepViewModel)
                }
                composable("WeatherScreen"){
                    weatherViewModel.startFetching()
                    WeatherScreen(navController = navController, viewModel = weatherViewModel)
                }
            }

            BackgroundImage(modifier = Modifier)
        }
    }
}
