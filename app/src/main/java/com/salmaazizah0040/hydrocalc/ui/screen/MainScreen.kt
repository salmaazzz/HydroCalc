package com.salmaazizah0040.hydrocalc.ui.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.salmaazizah0040.hydrocalc.ui.theme.HydroCalcTheme
import com.salmaazizah0040.hydrocalc.R
import com.salmaazizah0040.hydrocalc.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit
    ) {

    var showMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                actions = {
                    IconButton(onClick = { showMenu = true }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = stringResource(R.string.menu),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = if (isDarkTheme) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                    Text(
                                        text = if (isDarkTheme) {
                                            stringResource(R.string.mode_terang)
                                        } else {
                                            stringResource(R.string.mode_gelap)
                                        },
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                }
                            },
                            onClick = {
                                onToggleTheme()
                                showMenu = false
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Lightbulb,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                    Text(
                                        text = stringResource(R.string.tips_hidup_sehat),
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                }
                            },
                            onClick = {
                                showMenu = false
                                navController.navigate(Screen.Tips.route)
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                    Text(
                                        text = stringResource(R.string.tentang_aplikasi),
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                }
                            },
                            onClick = {
                                showMenu = false
                                navController.navigate(Screen.About.route)
                            }
                        )
                    }
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
    var berat by rememberSaveable { mutableStateOf("") }
    var beratError by rememberSaveable { mutableStateOf(false) }

    val radioOptions = listOf(
        stringResource(id = R.string.pria),
        stringResource(id = R.string.wanita)
    )
    var gender by rememberSaveable { mutableStateOf(radioOptions[0]) }

    var usia by rememberSaveable { mutableStateOf("") }
    var usiaError by rememberSaveable { mutableStateOf(false) }
    var expanded by rememberSaveable { mutableStateOf(false) }
    val usiaOptions = listOf(
        stringResource(id = R.string.rentang1),
        stringResource(id = R.string.rentang2),
        stringResource(id = R.string.rentang3),
        stringResource(id = R.string.rentang4),
        stringResource(id = R.string.rentang5),
        stringResource(id = R.string.rentang6),
        stringResource(id = R.string.rentang7),
        stringResource(id = R.string.rentang8)
    )
    var aktivitas by rememberSaveable { mutableStateOf("") }
    var aktivitasError by rememberSaveable { mutableStateOf(false) }
    var expandedAktivitas by rememberSaveable { mutableStateOf(false) }
    val aktivitasOptions = listOf(
        stringResource(id = R.string.ringan),
        stringResource(id = R.string.sedang),
        stringResource(id = R.string.berat)
    )
    var cuaca by rememberSaveable { mutableStateOf("") }
    var cuacaError by rememberSaveable { mutableStateOf(false) }
    var expandedCuaca by rememberSaveable { mutableStateOf(false) }
    val cuacaOptions = listOf(
        stringResource(id = R.string.panas),
        stringResource(id = R.string.normal),
        stringResource(id = R.string.dingin)
    )
    var kebutuhanAir by rememberSaveable { mutableIntStateOf(0) }

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
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
            onValueChange = {
                berat = it
                beratError = it.isBlank()
                            },
            label = { Text(text = stringResource(R.string.berat_badan)) },
            trailingIcon = { IconPicker(beratError, "kg") },
            supportingText = { ErrorHint(beratError) },
            isError = beratError,
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
                .padding(bottom = 16.dp)
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
                isError = usiaError,
                label = { Text(stringResource(R.string.usia)) },
                trailingIcon = {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = "Dropdown")
                },
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth(),
                supportingText = {
                    if (usiaError) {
                        Text(stringResource(R.string.usia_error), color = MaterialTheme.colorScheme.error)
                    }
                }
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
                            usiaError = false
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
                isError = aktivitasError,
                label = { Text(stringResource(R.string.aktivitas_fisik)) },
                trailingIcon = {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = "Dropdown")
                },
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth(),
                supportingText = {
                    if (aktivitasError) {
                        Text(stringResource(R.string.aktivitas_error), color = MaterialTheme.colorScheme.error)
                    }
                }
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
                            aktivitasError = false
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
                isError = cuacaError,
                label = { Text(stringResource(R.string.cuaca)) },
                trailingIcon = {
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = "Dropdown")
                },
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                    .fillMaxWidth(),
                supportingText = {
                    if (cuacaError) {
                        Text(stringResource(R.string.cuaca_error), color = MaterialTheme.colorScheme.error)
                    }
                }
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
                            cuacaError = false
                            expandedCuaca = false
                        }
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
        ) {
            Button(
                onClick = {
                    beratError = (berat == "" || berat == "0")
                    usiaError = usia.isBlank()
                    aktivitasError = aktivitas.isBlank()
                    cuacaError = cuaca.isBlank()

                    if (beratError || usiaError || aktivitasError || cuacaError) return@Button

                    val beratInt = berat.toIntOrNull() ?: 0

                    kebutuhanAir = hitungKebutuhanAir(
                        beratInt,
                        gender,
                        usia,
                        aktivitas,
                        cuaca,
                        context
                    )
                },
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(R.string.hitung))
            }

            Button(
                onClick = {
                    berat = ""
                    usia = ""
                    aktivitas = ""
                    cuaca = ""
                    kebutuhanAir = 0

                    beratError = false
                    usiaError = false
                    aktivitasError = false
                    cuacaError = false
                },
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            ) {
                Text(text = stringResource(R.string.reset))
            }
        }

        if (kebutuhanAir != 0) {
            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp
            )

            Text(
                text = stringResource(R.string.kebutuhan_air_label),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 12.dp)
            )

            Text(
                text = stringResource(R.string.kebutuhan_air_jumlah, kebutuhanAir),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(top = 8.dp)
            )

            Button(
                onClick = {
                    shareDate(
                        context = context,
                        message = context.getString(
                            R.string.bagikan_template,
                            berat,
                            gender,
                            usia,
                            aktivitas,
                            cuaca,
                            kebutuhanAir
                        )
                    )
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
            ) {
                Text(text = stringResource(R.string.bagikan))
            }
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
    berat: Int,
    gender: String,
    usia: String,
    aktivitas: String,
    cuaca: String,
    context: Context
): Int {
    val ringan = context.getString(R.string.ringan)
    val sedang = context.getString(R.string.sedang)
    val beratAkt = context.getString(R.string.berat)
    val dingin = context.getString(R.string.dingin)
    val normal = context.getString(R.string.normal)
    val panas = context.getString(R.string.panas)

    // dasar kebutuhan air berdasarkan usia dan gender
    var kebutuhan: Float = when (usia) {
        context.getString(R.string.rentang1) -> 900f
        context.getString(R.string.rentang2) -> 1400f
        context.getString(R.string.rentang3) -> if (gender == context.getString(R.string.pria)) 2200f else 1800f
        context.getString(R.string.rentang4) -> if (gender == context.getString(R.string.pria)) 2700f else 2000f
        context.getString(R.string.rentang5),
        context.getString(R.string.rentang6) -> if (gender == context.getString(R.string.pria)) 2500f else 2350f
        context.getString(R.string.rentang7) -> if (gender == context.getString(R.string.pria)) 2500f else 2300f
        context.getString(R.string.rentang8) -> if (gender == context.getString(R.string.pria)) 2100f else 1900f
        else -> 0f
    }

    // Penyesuaian berat badan → anggap ideal 60kg
    val beratIdeal = 60f
    val rasioBerat = berat.toFloat() / beratIdeal
    kebutuhan *= rasioBerat.coerceIn(0.8f, 1.4f)

    // penyesuaian aktivitas fisik
    kebutuhan *= when (aktivitas) {
        ringan -> 1.0f
        sedang -> 1.10f
        beratAkt -> 1.20f
        else -> 1.0f
    }

    // penyesuaian cuaca
    kebutuhan *= when (cuaca) {
        dingin -> 0.95f
        normal -> 1.0f
        panas -> 1.10f
        else -> 1.0f
    }

    return kebutuhan.toInt()
}

private fun shareDate(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}

@Composable
fun IconPicker(isError: Boolean, unit: String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(R.string.input_invalid))
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    HydroCalcTheme {
        MainScreen(rememberNavController(),
            isDarkTheme = false,
            onToggleTheme = {}
        )
    }
}