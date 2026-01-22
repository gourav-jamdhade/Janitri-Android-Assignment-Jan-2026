package com.example.janitriandroidassignnment.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.janitriandroidassignnment.data.VitalsEntry
import com.example.janitriandroidassignnment.ui.theme.AddVitalsDialogHeaderColor

@Composable
fun AddVitalsDialog(
    onDismiss: () -> Unit,
    onSubmit: (VitalsEntry) -> Unit
) {
    var sysBP by remember { mutableStateOf("") }
    var diaBP by remember { mutableStateOf("") }
    var heartRate by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var kicks by remember { mutableStateOf("") }

    val isFormValid = sysBP.isNotBlank() && diaBP.isNotBlank() && heartRate.isNotBlank()
            && weight.isNotBlank() && kicks.isNotBlank()

    Dialog(onDismissRequest = onDismiss) {
        Card {
            Column(Modifier.padding(16.dp)) {
                Text(
                    "Add Vitals", style = MaterialTheme.typography.headlineMedium,
                    color = AddVitalsDialogHeaderColor,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    VitalsTextField(
                        value = sysBP,
                        onValueChange = { sysBP = it },
                        label = "Sys BP",
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 4.dp)
                    )
                    VitalsTextField(
                        value = diaBP,
                        onValueChange = { diaBP = it },
                        label = "Dia BP",
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp)
                    )
                }
                VitalsTextField(
                    value = heartRate,
                    onValueChange = { heartRate = it },
                    label = "Heart Rate",
                    modifier = Modifier.fillMaxWidth()
                )
                VitalsTextField(
                    value = weight,
                    onValueChange = { weight = it },
                    label = "Weight (in kg)",
                    modifier = Modifier.fillMaxWidth()
                )
                VitalsTextField(
                    value = kicks,
                    onValueChange = { kicks = it },
                    label = "Baby Kicks",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Spacer(Modifier.width(8.dp))
                    Button(
                        enabled = isFormValid,
                        onClick = {
                            onSubmit(
                                VitalsEntry(
                                    systolicBP = sysBP.toIntOrNull() ?: 0,
                                    diastolicBP = diaBP.toIntOrNull() ?: 0,
                                    heartRate = heartRate.toIntOrNull() ?: 0,
                                    weight = weight.toFloatOrNull() ?: 0f,
                                    kickCount = kicks.toIntOrNull() ?: 0
                                )
                            )
                        },
                        shape = RoundedCornerShape(10.dp)
                    ) { Text("Submit") }
                }
            }
        }
    }
}