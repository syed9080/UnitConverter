package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {


    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    var iExpanded by remember { mutableStateOf(false) }
    var outputExpanded by remember { mutableStateOf(false) }
    var conversionFactory by remember { mutableStateOf(1.00) }
    var outputConversionFactory by remember { mutableStateOf(1.00) }

    fun convertFactor() {

        var inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        var result =
            (inputValueDouble * conversionFactory * 100 / outputConversionFactory).roundToInt() / 100.0
        outputValue = result.toString()


    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Unit Converter", style = MaterialTheme.typography.displaySmall)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = inputValue, onValueChange = {
                inputValue = it
                convertFactor()
            },
            label = { Text(text = "Input Value") },


        )
        Spacer(Modifier.height(16.dp))
        // To appear one below anoter
        Row {
            //To appear side by side
            Box {
                Button(onClick = { iExpanded = !iExpanded }) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                    DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                        DropdownMenuItem(text = { Text(text = "Centimeter") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Centimeter"
                                conversionFactory = 0.01
                                convertFactor()
                            })
                        DropdownMenuItem(text = { Text(text = "Meter") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Meter"
                                conversionFactory = 1.0
                                convertFactor()

                            })
                        DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactory = 0.3048
                            convertFactor()
                        })
                        DropdownMenuItem(text = { Text(text = "Millimeter") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "Millimeter"
                                conversionFactory = 0.001
                                convertFactor()
                            })
                    }

                }
            }
            Spacer(Modifier.width(16.dp))
            Box {
                Button(onClick = { outputExpanded = !outputExpanded }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                    DropdownMenu(
                        expanded = outputExpanded,
                        onDismissRequest = { outputExpanded = false }) {
                        DropdownMenuItem(text = { Text(text = "Centimeter") },
                            onClick = {
                                outputExpanded = false
                                outputUnit = "Centimeter"
                                outputConversionFactory = 0.01
                                convertFactor()
                            })
                        DropdownMenuItem(text = { Text(text = "Meter") },
                            onClick = {
                                outputExpanded = false
                                outputUnit = "Meter"
                                outputConversionFactory = 1.0
                                convertFactor()
                            })
                        DropdownMenuItem(text = { Text(text = "Feet") },
                            onClick = {
                                outputExpanded = false
                                outputUnit = "Feet"
                                outputConversionFactory = 0.3048
                                convertFactor()
                            })
                        DropdownMenuItem(text = { Text(text = "Millimeter") },
                            onClick = {
                                outputExpanded = false
                                outputUnit = "Millimeter"
                                outputConversionFactory = 0.001
                                convertFactor()
                            })
                    }
                }
            }
            Spacer(Modifier.width(16.dp))
        }
        Text(text = "Result: ${outputValue} $outputUnit",
            style = MaterialTheme.typography.bodyLarge,
            
            )
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    UnitConverter()
}


//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    UnitConverterTheme {
//        Greeting("Android")
//    }
//}