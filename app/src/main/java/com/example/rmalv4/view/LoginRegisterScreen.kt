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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.rmalv4.viewmodel.LoginRegisterViewModel

@Composable
fun LoginRegisterScreen(navController: NavController, viewModel: LoginRegisterViewModel){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedTextField(
            value = email,
            onValueChange = { email = it},
            label = { Text(text = "Email")}
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it},
            label = { Text(text = "password")},
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.signIn(email,password,navController)
            }
        ){
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                viewModel.register(email, password,navController)
            }
        ){
            Text(text = "Register")
        }
    }
}