package com.example.rmalv4.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LoginRegisterViewModel : ViewModel(){
    private val firebase = Firebase.auth

    fun signIn(email: String, password: String, navController: NavController) {
        firebase.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    navController.navigate("BMICalculatorScreen")
                    Log.d("LOGIN", "Successful Login")
                } else {
                    Log.d("LOGIN", "Unsuccessful Login: ${task.exception?.message}")
                }
            }
    }

    fun register(email: String, password: String, navController: NavController){
        firebase.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    signIn(email,password,navController)
                }else{
                    Log.e("REGISTER","Unsuccessful Registration: $task")
                }
            }
    }
}