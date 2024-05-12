package com.example.rmalv4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rmalv4.view.BMICalculatorScreen
import com.example.rmalv4.view.BackgroundImage
import com.example.rmalv4.view.StepCounterScreen
import com.example.rmalv4.viewmodel.BMIViewModel
import com.example.rmalv4.viewmodel.StepViewModel


class MainActivity : ComponentActivity() {
    private lateinit var stepViewModel: StepViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stepViewModel = ViewModelProvider(this)[StepViewModel::class.java]
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "BMICalculatorScreen" ){
                composable("BMICalculatorScreen"){
                    BMICalculatorScreen(navController , BMIViewModel())
                }
                composable("StepCounterScreen"){
                    stepViewModel.registerSensor()
                    StepCounterScreen(navController, viewModel = stepViewModel)
                }
            }

            BackgroundImage(modifier = Modifier)
        }
    }
}
