package com.example.rmalv4.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.AndroidViewModel
import com.example.rmalv4.model.StepCountModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlin.math.sqrt

class StepViewModel(application: Application) : AndroidViewModel(application), SensorEventListener{
    private val sensorManager: SensorManager = application.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    private val firestore = Firebase.firestore
    private val ref =  firestore.collection("PersonalInfo")
        .document(" 04WSZHG2rZ5YO9Il1JAh  ")

    private var peakDetected = false

    private val _stepCount = MutableLiveData<Int>()
    val stepCount: LiveData<Int> = _stepCount

    override fun onSensorChanged(event: SensorEvent?) {
        if(event?.sensor?.type == Sensor.TYPE_ACCELEROMETER){
            val valueX = event.values[0]
            val valueY = event.values[1]
            val valueZ = event.values[2]

            val magnitude = sqrt((valueX * valueX) + (valueY * valueY) + (valueZ * valueZ) )

            if(!peakDetected && magnitude > 13){
                peakDetected = true
                _stepCount.value = _stepCount.value?.plus(1)
                _stepCount.value?.let { saveStepCount(it) }
            }else if(magnitude < 13)
                peakDetected = false
        }

    }
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    private fun saveStepCount(stepCount: Int){
        val stepCountModel = StepCountModel(stepCount)
        ref.set(stepCountModel)
            .addOnSuccessListener { documentReference ->
                Log.d("FIREBASE", "StepCount added with ID: $documentReference")
            }
            .addOnFailureListener{ e ->
                Log.w("FIREBASE", "Error adding StepCount", e)
            }
    }

    fun registerSensor(){
        sensor?.also { accelerometer ->
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        }
        _stepCount.value = 0
    }
    fun unregisterSensor(){
        sensorManager.unregisterListener(this)
    }

}