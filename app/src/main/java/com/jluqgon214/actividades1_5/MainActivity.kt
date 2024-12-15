package com.jluqgon214.actividades1_5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.jluqgon214.actividades1_5.screens.Actividad3
import com.jluqgon214.actividades1_5.screens.ActividadPadre5
import com.jluqgon214.actividades1_5.ui.theme.Actividades15Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Actividades15Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Actividad3(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}