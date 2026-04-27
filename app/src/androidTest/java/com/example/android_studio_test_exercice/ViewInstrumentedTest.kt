package com.example.android_studio_test_exercice

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ViewInstrumentedUITest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun checkInitialComposableValues() {
        composeTestRule.onNodeWithTag("switch_wifi_id").assertIsOn()
        composeTestRule.onNodeWithTag("checkbox_carnivor_id").assertIsOn()
        composeTestRule.onNodeWithTag("checkbox_vegetaria_id").assert(hasClickAction())
        composeTestRule.onNodeWithTag("checkbox_vega_id").assertIsOff()
        composeTestRule.onNodeWithTag("tri_state_checkbox_id").assertIsOff()
        composeTestRule.onNodeWithTag("dropdown_trigger_id").assertTextEquals("Opció A")
        composeTestRule.onNodeWithTag("search_text_field_id").assertIsDisplayed()
        composeTestRule.onNodeWithTag("toggle_button_id").assertTextEquals("Desactivat")
        composeTestRule.onNodeWithTag("snackbar_text_id").assertDoesNotExist()
    }

    @Test
    fun testSwitchToggle() {
        composeTestRule.onNodeWithTag("switch_wifi_id").performClick()
        composeTestRule.onNodeWithTag("switch_wifi_id").assertIsOff()
        composeTestRule.onNodeWithTag("switch_wifi_id").performClick()
        composeTestRule.onNodeWithTag("switch_wifi_id").assertIsOn()
    }

    @Test
    fun testCheckboxVegetaria() {
        composeTestRule.onNodeWithTag("checkbox_vegetaria_id").performClick()
        composeTestRule.onNodeWithTag("checkbox_vegetaria_id").assertIsOn()
        composeTestRule.onNodeWithTag("checkbox_vegetaria_id").performClick()
        composeTestRule.onNodeWithTag("checkbox_vegetaria_id").assertIsOff()
    }

    @Test
    fun testCheckboxVega() {
        composeTestRule.onNodeWithTag("checkbox_vega_id").performClick()
        composeTestRule.onNodeWithTag("checkbox_vega_id").assertIsOn()
        composeTestRule.onNodeWithTag("checkbox_vega_id").performClick()
        composeTestRule.onNodeWithTag("checkbox_vega_id").assertIsOff()
    }

    @Test
    fun testTriStateCheckboxCycles() {
        // 1. Off -> Indeterminate
        composeTestRule.onNodeWithTag("tri_state_checkbox_id").performClick()
        composeTestRule.onNodeWithTag("tri_state_checkbox_id").assertIsDisplayed()

        // 2. Indeterminate -> On
        composeTestRule.onNodeWithTag("tri_state_checkbox_id").performClick()
        composeTestRule.onNodeWithTag("tri_state_checkbox_id").assertIsOn()

        // 3. On -> Off
        composeTestRule.onNodeWithTag("tri_state_checkbox_id").performClick()
        composeTestRule.onNodeWithTag("tri_state_checkbox_id").assertIsOff()
    }

    @Test
    fun testRadioButtons() {
        composeTestRule.onNodeWithTag("radio_Vinicius").assertIsNotEnabled()
        composeTestRule.onNodeWithTag("radio_Vinicius").performClick()
        composeTestRule.onNodeWithTag("radio_Vinicius").assertIsNotSelected()

        composeTestRule.onNodeWithTag("radio_Lamine_Yamal").performClick()
        composeTestRule.onNodeWithTag("radio_Lamine_Yamal").assertIsSelected()

        composeTestRule.onNodeWithTag("radio_Raphina").performClick()
        composeTestRule.onNodeWithTag("radio_Raphina").assertIsSelected()
        composeTestRule.onNodeWithTag("radio_Lamine_Yamal").assertIsNotSelected()
    }

    @Test
    fun testSlider() {
        composeTestRule.onNodeWithTag("slider_volume_id").performTouchInput { swipeLeft() }
        composeTestRule.onNodeWithTag("slider_volume_id").performTouchInput { swipeRight() }
    }

    @Test
    fun testDropdown() {
        composeTestRule.onNodeWithTag("dropdown_trigger_id").performClick()
        composeTestRule.onNodeWithTag("dropdown_item_Opció B").performClick()
        composeTestRule.onNodeWithTag("dropdown_trigger_id").assertTextEquals("Opció B")
    }

    @Test
    fun testSearchAndSnackbar() {
        composeTestRule.onNodeWithTag("search_text_field_id").performTextInput("Android")
        composeTestRule.onNodeWithTag("search_button_id").performClick()
        composeTestRule.onNodeWithTag("snackbar_text_id").assertExists()
        composeTestRule.onNodeWithTag("snackbar_text_id").assertTextEquals("Acció completada!")
    }

    @Test
    fun testEmptySearchDoesNotShowSnackbar() {
        composeTestRule.onNodeWithTag("search_text_field_id").performTextClearance()
        composeTestRule.onNodeWithTag("search_button_id").performClick()
        composeTestRule.onNodeWithTag("snackbar_text_id").assertDoesNotExist()
    }

    @Test
    fun testToggleButton() {
        composeTestRule.onNodeWithTag("toggle_button_id").assertTextEquals("Desactivat")
        composeTestRule.onNodeWithTag("toggle_button_id").performClick()
        composeTestRule.onNodeWithTag("toggle_button_id").assertTextEquals("Activat")
        composeTestRule.onNodeWithTag("toggle_button_id").performClick()
        composeTestRule.onNodeWithTag("toggle_button_id").assertTextEquals("Desactivat")
    }
}
