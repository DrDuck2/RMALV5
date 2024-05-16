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
import com.example.rmalv4.viewmodel.StepViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState


@Composable
fun StepCounterScreen(navController: NavController, viewModel: StepViewModel){

    val stepCount by viewModel.stepCount.observeAsState(initial = 0)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Step Count:",
            style = TextStyle(fontSize = 24.sp),
            modifier = Modifier
                .padding(bottom = 8.dp)
        )

        Text(
            text = stepCount.toString(),
            style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold)
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomStart
    ){
        Button(
            onClick = {
                navController.navigate("BMICalculatorScreen")
                viewModel.unregisterSensor()
            },
            modifier = Modifier
                .padding(16.dp)
        ){
            Text(text = "BMI Calculator")
        }
    }
}