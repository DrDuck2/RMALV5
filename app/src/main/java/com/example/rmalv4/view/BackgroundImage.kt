package com.example.rmalv4.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.rmalv4.R

@Composable
fun BackgroundImage(modifier: Modifier) {
    Box(modifier) {
        Image(
            painter = painterResource(id = R.drawable.fitness_resized),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.1f
        )
    }
}