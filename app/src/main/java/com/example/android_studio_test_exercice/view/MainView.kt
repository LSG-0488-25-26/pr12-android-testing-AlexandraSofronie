package com.example.android_studio_test_exercice.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_studio_test_exercice.viewmodel.MainViewModel

@Composable
fun MainView(myViewModel: MainViewModel, modifier: Modifier = Modifier) {
    val estatSwitch by myViewModel.estatSwitch.observeAsState(true)
    val esVegetaria by myViewModel.esVegetaria.observeAsState(true)
    val esVega by myViewModel.esVega.observeAsState(false)
    val esCarnivor by myViewModel.esCarnivor.observeAsState(true)
    val triStateStatus by myViewModel.triStateStatus.observeAsState(ToggleableState.Off)
    val selectedOption by myViewModel.selectedOption.observeAsState("Messi")

    /* TODO */
    val sliderValue by myViewModel.sliderValue.observeAsState(0f)
    val expanded by myViewModel.expanded.observeAsState(false)
    val selectedItem by myViewModel.selectedItem.observeAsState("Opció A")
    val searchText by myViewModel.searchText.observeAsState("")
    val showSnackbar by myViewModel.showSnackbar.observeAsState(false)
    val toggleState by myViewModel.toggleState.observeAsState(false)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 60.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Activar Wi-Fi: ",
                    modifier = Modifier
                        .fillMaxWidth(0.6f)
                        .padding(0.dp, 10.dp),
                    fontSize = 25.sp
                )

                Switch(
                    checked = estatSwitch,
                    onCheckedChange = { myViewModel.toggleEstatSwitch() },
                    modifier = Modifier.fillMaxWidth(0.4f).testTag("switch_wifi_id"),
                    enabled = true,
                    colors = SwitchDefaults.colors(
                        uncheckedThumbColor = Color.LightGray,
                        checkedThumbColor = Color.Black
                    )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize()
                    .padding(0.dp, 20.dp)
            ) {
                Text(
                    text = "Opcions de menú:",
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 25.sp
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 10.dp)
                ) {
                    Text("Carnívor/a", Modifier.align(CenterVertically).fillMaxWidth(0.33f))
                    Text("Vegetarià/na", Modifier.align(CenterVertically).fillMaxWidth(0.6f))
                    Text("Vegà/na", Modifier.align(CenterVertically).fillMaxWidth(1f))
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth()
                ) {
                    Checkbox(
                        checked = esCarnivor,
                        onCheckedChange = { myViewModel.toggleEsCarnivor() },
                        modifier = Modifier.fillMaxWidth(0.20f).testTag("checkbox_carnivor_id"),
                        enabled = false,
                        colors = CheckboxDefaults.colors(
                            uncheckedColor = Color.LightGray,
                            checkmarkColor = Color.Black
                        )
                    )
                    Checkbox(
                        checked = esVegetaria,
                        onCheckedChange = { myViewModel.toggleEsVegetaria() },
                        modifier = Modifier.fillMaxWidth(0.33f).testTag("checkbox_vegetaria_id"),
                        enabled = true,
                        colors = CheckboxDefaults.colors(
                            uncheckedColor = Color.LightGray,
                            checkmarkColor = Color.Black
                        )
                    )
                    Checkbox(
                        checked = esVega,
                        onCheckedChange = { myViewModel.toggleEsVega() },
                        modifier = Modifier.fillMaxWidth(0.33f).testTag("checkbox_vega_id"),
                        enabled = true,
                        colors = CheckboxDefaults.colors(
                            uncheckedColor = Color.LightGray,
                            checkmarkColor = Color.Black
                        )
                    )
                }
            }

            Column(modifier = Modifier.fillMaxWidth()) {
                Text("TriState", Modifier.fillMaxWidth(), fontSize = 20.sp)
                TriStateCheckbox(
                    state = triStateStatus,
                    onClick = { myViewModel.toggleTriStateStatus() },
                    modifier = Modifier.testTag("tri_state_checkbox_id")
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text("Pilota d'Or:", fontSize = 20.sp)

                listOf("Vinicius", "Lamine Yamal", "Raphina").forEach { player ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedOption == player,
                            onClick = { if (player != "Vinicius") myViewModel.setSelectedOption(player) },
                            enabled = player != "Vinicius", // Deshabilitat
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color.Black,
                                unselectedColor = Color.LightGray
                            ),
                            modifier = Modifier.testTag("radio_${player.replace(" ", "_")}")
                        )
                        Text(player, Modifier.padding(start = 8.dp))
                    }
                }
            }

            Text("Volum: ${sliderValue.toInt()}%")
            Slider(
                value = sliderValue,
                onValueChange = { myViewModel.setSliderValue(it) },
                valueRange = 0f..100f,
                modifier = Modifier.testTag("slider_volume_id")
            )

            Box(modifier = Modifier.wrapContentSize()) {
                Text(
                    text = selectedItem,
                    modifier = Modifier.clickable { myViewModel.setExpanded(true) }.testTag("dropdown_trigger_id")
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { myViewModel.setExpanded(false) }
                ) {
                    listOf("Opció A", "Opció B", "Opció C").forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                myViewModel.setSelectedItem(option)
                                myViewModel.setExpanded(false)
                            },
                            modifier = Modifier.testTag("dropdown_item_$option")
                        )
                    }
                }
            }

            OutlinedTextField(
                value = searchText,
                onValueChange = { myViewModel.setSearchText(it) },
                label = {Text("Buscar...")},
                modifier = Modifier.testTag("search_text_field_id")
            )
            Button(
                onClick = { myViewModel.performSearch() },
                modifier = Modifier.testTag("search_button_id")
            ) {
                Text("Buscar")
            }

            if (showSnackbar) {
                Text(
                    text = "Acció completada!",
                    color = Color.Green,
                    modifier = Modifier.testTag("snackbar_text_id")
                )
            }

            Button(
                onClick = { myViewModel.toggle() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (toggleState) Color.Green else Color.Red
                ),
                modifier = Modifier.testTag("toggle_button_id")
            ) {
                Text(if (toggleState) "Activat" else "Desactivat")
            }
        }
    }
}
