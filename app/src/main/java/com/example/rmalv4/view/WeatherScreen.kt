package com.example.rmalv4.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rmalv4.viewmodel.WeatherViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.AbsoluteAlignment

@Composable
fun WeatherScreen(navController: NavController, viewModel: WeatherViewModel){

    val weatherData by viewModel.weatherData.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Osijek Weather",
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
        )

        Spacer(modifier = Modifier.height(20.dp))

        weatherData?.let {
            Text(
                text = it.main,
                style = TextStyle(fontSize = 24.sp),
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        weatherData?.let {
            Text(
                text = "Description: ${it.description}",
                style = TextStyle(fontSize = 24.sp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Temperature: ${weatherData?.temp.toString()}",
            style = TextStyle(fontSize = 24.sp)
        )
    }

    Box(modifier = Modifier.fillMaxSize(),contentAlignment = AbsoluteAlignment.BottomLeft){
        Button(
            onClick = {
                viewModel.stopFetching()
                navController.navigate("BMICalculatorScreen")
            },
            modifier = Modifier
                .padding(16.dp)
        ){
            Text(text = "BMI")
        }
    }
}