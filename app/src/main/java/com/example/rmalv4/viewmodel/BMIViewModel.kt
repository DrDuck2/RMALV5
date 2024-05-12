package com.example.rmalv4.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.rmalv4.model.BMIModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class BMIViewModel : ViewModel() {
    private val firestore = Firebase.firestore

    private fun saveBMIRecord(bmiRecord: BMIModel){
        firestore.collection("PersonalInfo")
            .document(" BO8jDvRN5X0sWyWUxo5Y ")
            .set(bmiRecord)
            .addOnSuccessListener { documentReference ->
                Log.d("FIREBASE", "BMIRecord added with ID: $documentReference")
            }
            .addOnFailureListener{ e ->
                Log.w("FIREBASE", "Error adding BMIRecord", e)
            }
    }

    fun calculateBMI(weight: Float?, height: Float?): Float?{
        return if (weight != null && height != null && height > 0){
            val bmi = weight / ((height/100)*(height/100))
            val bmiRecord = BMIModel(weight, height, bmi)
            saveBMIRecord(bmiRecord)
            bmi
        }else{
            null
        }
    }
}