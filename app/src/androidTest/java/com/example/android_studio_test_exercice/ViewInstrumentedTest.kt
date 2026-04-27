package com.example.android_studio_test_exercice

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Clase de pruebas instrumentadas para validar la interfaz de usuario (MainView).
 * Estas pruebas se ejecutan en un dispositivo o emulador y verifican que los componentes
 * visuales respondan correctamente a las interacciones del usuario.
 */
@RunWith(AndroidJUnit4::class)
class ViewInstrumentedUITest {

    /**
     * Regla de JUnit que proporciona el entorno de pruebas para Compose.
     * Permite lanzar la MainActivity y realizar búsquedas y acciones sobre los nodos de la UI.
     */
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    /**
     * Verifica que los componentes principales de la pantalla se muestren con sus
     * valores o estados iniciales esperados al arrancar la aplicación.
     */
    @Test
    fun checkInitialComposableValues() {
        // Verifica estados de selección y activación iniciales
        composeTestRule.onNodeWithTag("switch_wifi_id").assertIsOn()
        composeTestRule.onNodeWithTag("checkbox_carnivor_id").assertIsOn()
        composeTestRule.onNodeWithTag("checkbox_vegetaria_id").assert(hasClickAction())
        composeTestRule.onNodeWithTag("checkbox_vega_id").assertIsOff()
        composeTestRule.onNodeWithTag("tri_state_checkbox_id").assertIsOff()
        
        // Verifica textos por defecto en menús y campos
        composeTestRule.onNodeWithTag("dropdown_trigger_id").assertTextEquals("Opció A")
        composeTestRule.onNodeWithTag("search_text_field_id").assertIsDisplayed()
        composeTestRule.onNodeWithTag("toggle_button_id").assertTextEquals("Desactivat")
        
        // Asegura que no existan elementos que solo deben aparecer tras una acción
        composeTestRule.onNodeWithTag("snackbar_text_id").assertDoesNotExist()
    }

    /**
     * Prueba la funcionalidad del interruptor (Switch) de Wi-Fi,
     * comprobando que alterna su estado (On/Off) tras cada pulsación.
     */
    @Test
    fun testSwitchToggle() {
        composeTestRule.onNodeWithTag("switch_wifi_id").performClick()
        composeTestRule.onNodeWithTag("switch_wifi_id").assertIsOff()
        composeTestRule.onNodeWithTag("switch_wifi_id").performClick()
        composeTestRule.onNodeWithTag("switch_wifi_id").assertIsOn()
    }

    /**
     * Prueba el funcionamiento del Checkbox vegetariano.
     */
    @Test
    fun testCheckboxVegetaria() {
        composeTestRule.onNodeWithTag("checkbox_vegetaria_id").performClick()
        composeTestRule.onNodeWithTag("checkbox_vegetaria_id").assertIsOn()
        composeTestRule.onNodeWithTag("checkbox_vegetaria_id").performClick()
        composeTestRule.onNodeWithTag("checkbox_vegetaria_id").assertIsOff()
    }

    /**
     * Prueba el funcionamiento del Checkbox vegano.
     */
    @Test
    fun testCheckboxVega() {
        composeTestRule.onNodeWithTag("checkbox_vega_id").performClick()
        composeTestRule.onNodeWithTag("checkbox_vega_id").assertIsOn()
        composeTestRule.onNodeWithTag("checkbox_vega_id").performClick()
        composeTestRule.onNodeWithTag("checkbox_vega_id").assertIsOff()
    }

    /**
     * Valida el ciclo de estados del TriStateCheckbox (Off -> Indeterminado -> On -> Off).
     */
    @Test
    fun testTriStateCheckboxCycles() {
        // 1. Pasa de Desactivado a Indeterminado
        composeTestRule.onNodeWithTag("tri_state_checkbox_id").performClick()
        composeTestRule.onNodeWithTag("tri_state_checkbox_id").assertIsDisplayed()

        // 2. Pasa de Indeterminado a Activado
        composeTestRule.onNodeWithTag("tri_state_checkbox_id").performClick()
        composeTestRule.onNodeWithTag("tri_state_checkbox_id").assertIsOn()

        // 3. Vuelve a Desactivado
        composeTestRule.onNodeWithTag("tri_state_checkbox_id").performClick()
        composeTestRule.onNodeWithTag("tri_state_checkbox_id").assertIsOff()
    }

    /**
     * Prueba los botones de opción (RadioButtons).
     * Verifica que el botón deshabilitado no responda y que al seleccionar una opción,
     * la anterior deje de estar seleccionada.
     */
    @Test
    fun testRadioButtons() {
        // Verifica restricción sobre opción deshabilitada
        composeTestRule.onNodeWithTag("radio_Vinicius").assertIsNotEnabled()
        composeTestRule.onNodeWithTag("radio_Vinicius").performClick()
        composeTestRule.onNodeWithTag("radio_Vinicius").assertIsNotSelected()

        // Prueba selección de opciones válidas
        composeTestRule.onNodeWithTag("radio_Lamine_Yamal").performClick()
        composeTestRule.onNodeWithTag("radio_Lamine_Yamal").assertIsSelected()

        composeTestRule.onNodeWithTag("radio_Raphina").performClick()
        composeTestRule.onNodeWithTag("radio_Raphina").assertIsSelected()
        composeTestRule.onNodeWithTag("radio_Lamine_Yamal").assertIsNotSelected()
    }

    /**
     * Prueba la respuesta visual del control deslizante (Slider) ante gestos de arrastre.
     */
    @Test
    fun testSlider() {
        composeTestRule.onNodeWithTag("slider_volume_id").performTouchInput { swipeLeft() }
        composeTestRule.onNodeWithTag("slider_volume_id").performTouchInput { swipeRight() }
    }

    /**
     * Prueba el funcionamiento del menú desplegable (Dropdown).
     * Verifica que al seleccionar una opción, el texto del disparador se actualice.
     */
    @Test
    fun testDropdown() {
        composeTestRule.onNodeWithTag("dropdown_trigger_id").performClick()
        composeTestRule.onNodeWithTag("dropdown_item_Opció B").performClick()
        composeTestRule.onNodeWithTag("dropdown_trigger_id").assertTextEquals("Opció B")
    }

    /**
     * Prueba la entrada de texto en el buscador y el botón de búsqueda.
     * Verifica que aparezca el mensaje de éxito (Simulación de Snackbar).
     */
    @Test
    fun testSearchAndSnackbar() {
        composeTestRule.onNodeWithTag("search_text_field_id").performTextInput("Android")
        composeTestRule.onNodeWithTag("search_button_id").performClick()
        composeTestRule.onNodeWithTag("snackbar_text_id").assertExists()
        composeTestRule.onNodeWithTag("snackbar_text_id").assertTextEquals("Acció completada!")
    }

    /**
     * Verifica que si se intenta buscar con el campo vacío, no aparezca el mensaje de éxito.
     */
    @Test
    fun testEmptySearchDoesNotShowSnackbar() {
        composeTestRule.onNodeWithTag("search_text_field_id").performTextClearance()
        composeTestRule.onNodeWithTag("search_button_id").performClick()
        composeTestRule.onNodeWithTag("snackbar_text_id").assertDoesNotExist()
    }

    /**
     * Prueba el botón de tipo conmutador (Toggle) final de la interfaz.
     * Verifica que el texto del botón cambie entre "Activat" y "Desactivat".
     */
    @Test
    fun testToggleButton() {
        composeTestRule.onNodeWithTag("toggle_button_id").assertTextEquals("Desactivat")
        composeTestRule.onNodeWithTag("toggle_button_id").performClick()
        composeTestRule.onNodeWithTag("toggle_button_id").assertTextEquals("Activat")
        composeTestRule.onNodeWithTag("toggle_button_id").performClick()
        composeTestRule.onNodeWithTag("toggle_button_id").assertTextEquals("Desactivat")
    }
}
