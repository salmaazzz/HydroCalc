package com.salmaazizah0040.hydrocalc.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.salmaazizah0040.hydrocalc.R
import com.salmaazizah0040.hydrocalc.ui.theme.HydroCalcTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innerPadding ->
        ScreenContent(Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
    var berat by remember { mutableStateOf("") }

    val radioOptions = listOf(
        stringResource(id = R.string.pria),
        stringResource(id = R.string.wanita)
    )
    var gender by remember { mutableStateOf(radioOptions[0]) }

    var usia by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val usiaOptions = listOf(
        stringResource(id = R.string.rentang1),
        stringResource(id = R.string.rentang2),
        stringResource(id = R.string.rentang3),
        stringResource(id = R.string.rentang4),
        stringResource(id = R.string.rentang5)
    )

    var aktivitas by remember { mutableStateOf("") }
    var expandedAktivitas by remember { mutableStateOf(false) }
    val aktivitasOptions = listOf(
        stringResource(id = R.string.ringan),
        stringResource(id = R.string.sedang),
        stringResource(id = R.string.berat)
    )

    var cuaca by remember { mutableStateOf("") }
    var expandedCuaca by remember { mutableStateOf(false) }
    val cuacaOptions = listOf(
        stringResource(id = R.string.panas),
        stringResource(id = R.string.normal),
        stringResource(id = R.string.dingin)
    )

    var kebutuhanAir by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.hydrocalc_intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = berat,
            onValueChange = { berat = it },
            label = { Text(text = stringResource(R.string.berat_badan)) },
            trailingIcon = { Text(text = "kg") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .padding(top = 6.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            radioOptions.forEach { text ->
                GenderOption(
                    label = text,
                    isSelected = gender == text,
                    modifier = Modifier
                        .selectable(
                            selected = gender == text,
                            onClick = { gender = text },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = usia,
                onValueChange = {},
                readOnly = true,
                label = { Text(stringResource(R.string.usia)) },
                trailingIcon = {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = "Dropdown")
                },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                usiaOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            usia = option
                            expanded = false
                        }
                    )
                }
            }
        }
        ExposedDropdownMenuBox(
            expanded = expandedAktivitas,
            onExpandedChange = { expandedAktivitas = !expandedAktivitas },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = aktivitas,
                onValueChange = {},
                readOnly = true,
                label = { Text(stringResource(R.string.aktivitas_fisik)) },
                trailingIcon = {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = "Dropdown")
                },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expandedAktivitas,
                onDismissRequest = { expandedAktivitas = false }
            ) {
                aktivitasOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            aktivitas = option
                            expandedAktivitas = false
                        }
                    )
                }
            }
        }
        ExposedDropdownMenuBox(
            expanded = expandedCuaca,
            onExpandedChange = { expandedCuaca = !expandedCuaca },
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = cuaca,
                onValueChange = {},
                readOnly = true,
                label = { Text(stringResource(R.string.cuaca)) },
                trailingIcon = {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = "Dropdown")
                },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expandedCuaca,
                onDismissRequest = { expandedCuaca = false }
            ) {
                cuacaOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(option) },
                        onClick = {
                            cuaca = option
                            expandedCuaca = false
                        }
                    )
                }
            }
        }
        Button(
            onClick = {
                val beratFloat = berat.toFloatOrNull() ?: 0f
                kebutuhanAir = hitungKebutuhanAir(
                    beratFloat,
                    gender,
                    usia,
                    aktivitas,
                    cuaca
                )
            },
            modifier = Modifier.padding(top = 10.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(R.string.hitung))
        }

        if (kebutuhanAir != 0) {
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp
            )
            Text(
                text = "Kebutuhan air minum Anda adalah:",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "$kebutuhanAir ml per hari",
                style = MaterialTheme.typography.headlineLarge
            )
        }
    }
}

@Composable
fun GenderOption(label: String, isSelected: Boolean, modifier: Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = isSelected, onClick = null)
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

fun hitungKebutuhanAir(
    berat: Float,
    gender: String,
    usia: String,
    aktivitas: String,
    cuaca: String
): Int {
    // dasar kebutuhan air berdasarkan usia dan gender
    var kebutuhan: Float = when (usia) {
        "1-3 tahun" -> 900f
        "4-8 tahun" -> 1400f
        "9-13 tahun" -> if (gender == "Pria") 2200f else 1800f
        "14-18 tahun" -> if (gender == "Pria") 2700f else 2000f
        "19 tahun ke atas" -> berat * 30f
        else -> 0f
    }

    // penyesuaian aktivitas fisik
    kebutuhan *= when (aktivitas) {
        "Ringan" -> 1.0f
        "Sedang" -> 1.10f
        "Berat" -> 1.20f
        else -> 1.0f
    }

    // penyesuaian cuaca
    kebutuhan += when (cuaca) {
        "Dingin" -> 0.95f
        "Normal" -> 1.0f
        "Panas" -> 1.10f
        else -> 1.0f
    }

    return kebutuhan.toInt()
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    HydroCalcTheme {
        MainScreen()
    }
}