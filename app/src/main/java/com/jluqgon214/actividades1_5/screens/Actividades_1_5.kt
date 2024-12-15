package com.jluqgon214.actividades1_5.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

/*
Actividad 1:
Hacer que el texto del botón muestre "Cargar perfil", pero cambie a "Cancelar"
cuando se muestre la línea de progreso... Cuando pulsemos "Cancelar" vuelve al texto por defecto.
*/
//@Preview(showBackground = true)
@Composable
fun Actividad1(modifier: Modifier) {
    var showLoading by rememberSaveable { mutableStateOf(false) }

    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (showLoading) {
            CircularProgressIndicator(
                color = Color.Red,
                strokeWidth = 10.dp
            )
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 32.dp),
                color = Color.Red,
                trackColor = Color.LightGray
            )
        }

        Button(
            onClick = { showLoading = !showLoading }
        ) {
            Text(text = (if (showLoading) "Cancelar" else "Cargar perfil"))
        }
    }
}

/*
Actividad 2:
Modifica ahora también que se separe el botón de la línea de progreso 30 dp,
pero usando un estado... es decir, cuando no sea visible no quiero que tenga la separación
aunque no se vea.
*/

@Composable
fun Actividad2(modifier: Modifier) {
    var showLoading by rememberSaveable { mutableStateOf(false) }

    Column(
        Modifier
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (showLoading) {
            CircularProgressIndicator(
                color = Color.Red,
                strokeWidth = 10.dp
            )
            LinearProgressIndicator(
                modifier = Modifier.padding(top = 32.dp),
                color = Color.Red,
                trackColor = Color.LightGray
            )
        }

        Button(
            modifier = Modifier.padding(top = if (showLoading) 30.dp else 0.dp),
            onClick = { showLoading = !showLoading }
        ) {
            Text(text = (if (showLoading) "Cancelar" else "Cargar perfil"))
        }
    }
}


/*
Actividad 3:
- Separar los botones entre ellos 10 dp, del indicador de progreso 15 dp y centrarlos horizontalmente.
- Cuando se clique el botón Incrementar, debe añadir 0.1 a la propiedad progress y quitar 0.1
  cuando se pulse el botón Decrementar.
- Evitar que nos pasemos de los márgenes de su propiedad progressStatus (0-1)
*/
@Composable
fun Actividad3(modifier: Modifier) {
    var progress by remember { mutableStateOf(0f) }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(15.dp)) // Espacio entre el indicador y los botones
        LinearProgressIndicator(progress = progress)
        Spacer(modifier = Modifier.height(15.dp)) // Espacio entre el indicador y los botones

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp), // Centra los botones horizontalmente
            horizontalArrangement = Arrangement.Center // Centra los botones horizontalmente
        ) {
            Button(
                onClick = {
                    progress = (progress + 0.1f).coerceAtMost(1f) // Incrementa y limita a 1
                },
                modifier = Modifier.weight(1f) // Distribuye el espacio entre los botones
            ) {
                Text(text = "Incrementar")
            }
            Spacer(modifier = Modifier.width(10.dp)) // Espacio entre los botones
            Button(
                onClick = {
                    progress = (progress - 0.1f).coerceAtLeast(0f) // Decrementa y limita a 0
                },
                modifier = Modifier.weight(1f) // Distribuye el espacio entre los botones
            ) {
                Text(text = "Reducir")
            }
        }
    }
}


/*
Actividad 4:
Sitúa el TextField en el centro de la pantalla y haz que reemplace el valor de una coma por un punto
y que no deje escribir más de un punto decimal...
*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Actividad4(modifier: Modifier) {
    var myVal by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = myVal,
            onValueChange = { newValue ->
                // Reemplaza la coma por un punto
                val sanitizedValue = newValue.replace(',', '.')

                // Permite solo un punto decimal
                if (sanitizedValue.count { it == '.' } <= 1) {
                    myVal = sanitizedValue
                }
            },
            label = { Text(text = "Importe") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // Teclado numérico
        )
    }
}


/*
Actividad 5:
Haz lo mismo, pero elevando el estado a una función superior y usando un componente OutlinedTextField
al que debes añadir un padding alrededor de 15 dp y establecer colores diferentes en los bordes
cuando tenga el foco y no lo tenga.
A nivel funcional no permitas que se introduzcan caracteres que invaliden un número decimal.
*/
//Funcion padre
@Composable
fun ActividadPadre5(modifier: Modifier = Modifier) {
    var importe by rememberSaveable { mutableStateOf("") }

    Actividad5(
        importe = importe,
        onImporteChange = { importe = it }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Actividad5(
    importe: String,
    onImporteChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = importe,
            onValueChange = { newValue ->
                // Reemplaza la coma por un punto
                val sanitizedValue = newValue.replace(',', '.')

                // Permite solo un punto decimal y caracteres numéricos
                if (sanitizedValue.matches(Regex("^[0-9]*([.][0-9]*)?$"))) {
                    onImporteChange(sanitizedValue)
                }
            },
            label = { Text(text = "Importe") },
            modifier = modifier
                .padding(15.dp)
                .border(
                    width = 1.dp,
                    color = if (isFocused) Color.Blue else Color.Gray,
                    shape = RoundedCornerShape(4.dp)
                ),
            interactionSource = interactionSource,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

