package com.example.android_studio_test_exercice

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.state.ToggleableState
import com.example.android_studio_test_exercice.viewmodel.MainViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Clase de pruebas unitarias para el MainViewModel.
 * Se encarga de verificar que la lógica de negocio y los cambios de estado
 * del ViewModel funcionen correctamente de forma aislada.
 */
class MainViewModelTest {

    /**
     * Regla que permite que las pruebas de LiveData se ejecuten de forma instantánea
     * en el mismo hilo, evitando errores de ejecución asíncrona en los tests.
     */
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    /**
     * Configuración inicial antes de cada test.
     * Crea una nueva instancia del MainViewModel para asegurar la independencia entre pruebas.
     */
    @Before
    fun setup() {
        viewModel = MainViewModel()
    }

    /**
     * Verifica que todos los valores iniciales del ViewModel sean los correctos
     * al instanciar la clase por primera vez.
     */
    @Test
    fun testInitialValues() {
        Assert.assertEquals(true, viewModel.estatSwitch.value)
        Assert.assertEquals(false, viewModel.esVegetaria.value)
        Assert.assertEquals(false, viewModel.esVega.value)
        Assert.assertEquals(true, viewModel.esCarnivor.value)
        Assert.assertEquals(ToggleableState.Off, viewModel.triStateStatus.value)
        Assert.assertEquals("Messi", viewModel.selectedOption.value)
        Assert.assertEquals(0f, viewModel.sliderValue.value)
        Assert.assertEquals(false, viewModel.expanded.value)
        Assert.assertEquals("Opció A", viewModel.selectedItem.value)
        Assert.assertEquals("", viewModel.searchText.value)
        Assert.assertEquals(false, viewModel.showSnackbar.value)
        Assert.assertEquals(false, viewModel.toggleState.value)
    }

    /**
     * Prueba la función toggleEstatSwitch para asegurar que cambia
     * correctamente el estado del switch de Wi-Fi.
     */
    @Test
    fun testToggleEstatSwitch() {
        viewModel.toggleEstatSwitch()
        Assert.assertEquals(false, viewModel.estatSwitch.value)
        viewModel.toggleEstatSwitch()
        Assert.assertEquals(true, viewModel.estatSwitch.value)
    }

    /**
     * Prueba la función toggleEsCarnivor para asegurar que cambia
     * el estado del checkbox carnívoro.
     */
    @Test
    fun testToggleEsCarnivor() {
        viewModel.toggleEsCarnivor()
        Assert.assertEquals(false, viewModel.esCarnivor.value)
        viewModel.toggleEsCarnivor()
        Assert.assertEquals(true, viewModel.esCarnivor.value)
    }

    /**
     * Prueba la función toggleEsVegetaria para asegurar que cambia
     * el estado del checkbox vegetariano.
     */
    @Test
    fun testToggleEsVegetaria() {
        viewModel.toggleEsVegetaria()
        Assert.assertEquals(true, viewModel.esVegetaria.value)
        viewModel.toggleEsVegetaria()
        Assert.assertEquals(false, viewModel.esVegetaria.value)
    }

    /**
     * Prueba la función toggleEsVega para asegurar que cambia
     * el estado del checkbox vegano.
     */
    @Test
    fun testToggleEsVega() {
        viewModel.toggleEsVega()
        Assert.assertEquals(true, viewModel.esVega.value)
        viewModel.toggleEsVega()
        Assert.assertEquals(false, viewModel.esVega.value)
    }

    /**
     * Prueba el ciclo de estados del TriStateCheckbox:
     * De Off pasa a Indeterminate, luego a On y vuelve a Off.
     */
    @Test
    fun testToggleTriStateStatus() {
        viewModel.toggleTriStateStatus()
        Assert.assertEquals(ToggleableState.Indeterminate, viewModel.triStateStatus.value)
        viewModel.toggleTriStateStatus()
        Assert.assertEquals(ToggleableState.On, viewModel.triStateStatus.value)
        viewModel.toggleTriStateStatus()
        Assert.assertEquals(ToggleableState.Off, viewModel.triStateStatus.value)
    }

    /**
     * Verifica que se actualice correctamente la opción seleccionada en los RadioButtons.
     */
    @Test
    fun testSetSelectedOption() {
        viewModel.setSelectedOption("Raphina")
        Assert.assertEquals("Raphina", viewModel.selectedOption.value)
    }

    /**
     * Verifica que se actualice correctamente el valor del slider.
     */
    @Test
    fun testSetSliderValue() {
        viewModel.setSliderValue(42f)
        Assert.assertEquals(42f, viewModel.sliderValue.value)
    }

    /**
     * Verifica que cambie el estado de expansión (abierto/cerrado) del menú desplegable.
     */
    @Test
    fun testSetExpanded() {
        viewModel.setExpanded(true)
        Assert.assertEquals(true, viewModel.expanded.value)
        viewModel.setExpanded(false)
        Assert.assertEquals(false, viewModel.expanded.value)
    }

    /**
     * Verifica que se actualice el elemento seleccionado del menú desplegable.
     */
    @Test
    fun testSetSelectedItem() {
        viewModel.setSelectedItem("Opció B")
        Assert.assertEquals("Opció B", viewModel.selectedItem.value)
    }

    /**
     * Verifica que se actualice el texto introducido en el campo de búsqueda.
     */
    @Test
    fun testSetSearchText() {
        viewModel.setSearchText("Hola")
        Assert.assertEquals("Hola", viewModel.searchText.value)
    }

    /**
     * Comprueba que al realizar una búsqueda con texto válido se active el snackbar de confirmación.
     */
    @Test
    fun testPerformSearch_withNonEmptyText() {
        viewModel.setSearchText("Kotlin")
        viewModel.performSearch()
        Assert.assertEquals(true, viewModel.showSnackbar.value)
    }

    /**
     * Comprueba que al intentar buscar con un texto vacío o con espacios
     * no se muestre el snackbar de confirmación.
     */
    @Test
    fun testPerformSearch_withEmptyText() {
        viewModel.setSearchText("")
        viewModel.performSearch()
        Assert.assertEquals(false, viewModel.showSnackbar.value)

        viewModel.setSearchText("   ")
        viewModel.performSearch()
        Assert.assertEquals(false, viewModel.showSnackbar.value)
    }

    /**
     * Prueba la funcionalidad del botón toggle final, verificando que alterna su estado.
     */
    @Test
    fun testToggle() {
        viewModel.toggle()
        Assert.assertEquals(true, viewModel.toggleState.value)
        viewModel.toggle()
        Assert.assertEquals(false, viewModel.toggleState.value)
    }
}
