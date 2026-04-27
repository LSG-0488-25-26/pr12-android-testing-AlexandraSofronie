package com.example.android_studio_test_exercice

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.state.ToggleableState
import com.example.android_studio_test_exercice.viewmodel.MainViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel()
    }

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

    @Test
    fun testToggleEstatSwitch() {
        viewModel.toggleEstatSwitch()
        Assert.assertEquals(false, viewModel.estatSwitch.value)
        viewModel.toggleEstatSwitch()
        Assert.assertEquals(true, viewModel.estatSwitch.value)
    }

    @Test
    fun testToggleEsCarnivor() {
        viewModel.toggleEsCarnivor()
        Assert.assertEquals(false, viewModel.esCarnivor.value)
        viewModel.toggleEsCarnivor()
        Assert.assertEquals(true, viewModel.esCarnivor.value)
    }

    @Test
    fun testToggleEsVegetaria() {
        viewModel.toggleEsVegetaria()
        Assert.assertEquals(true, viewModel.esVegetaria.value)
        viewModel.toggleEsVegetaria()
        Assert.assertEquals(false, viewModel.esVegetaria.value)
    }

    @Test
    fun testToggleEsVega() {
        viewModel.toggleEsVega()
        Assert.assertEquals(true, viewModel.esVega.value)
        viewModel.toggleEsVega()
        Assert.assertEquals(false, viewModel.esVega.value)
    }

    @Test
    fun testToggleTriStateStatus() {
        viewModel.toggleTriStateStatus()
        Assert.assertEquals(ToggleableState.Indeterminate, viewModel.triStateStatus.value)
        viewModel.toggleTriStateStatus()
        Assert.assertEquals(ToggleableState.On, viewModel.triStateStatus.value)
        viewModel.toggleTriStateStatus()
        Assert.assertEquals(ToggleableState.Off, viewModel.triStateStatus.value)
    }

    @Test
    fun testSetSelectedOption() {
        viewModel.setSelectedOption("Raphina")
        Assert.assertEquals("Raphina", viewModel.selectedOption.value)
    }

    @Test
    fun testSetSliderValue() {
        viewModel.setSliderValue(42f)
        Assert.assertEquals(42f, viewModel.sliderValue.value)
    }

    @Test
    fun testSetExpanded() {
        viewModel.setExpanded(true)
        Assert.assertEquals(true, viewModel.expanded.value)
        viewModel.setExpanded(false)
        Assert.assertEquals(false, viewModel.expanded.value)
    }

    @Test
    fun testSetSelectedItem() {
        viewModel.setSelectedItem("Opció B")
        Assert.assertEquals("Opció B", viewModel.selectedItem.value)
    }

    @Test
    fun testSetSearchText() {
        viewModel.setSearchText("Hola")
        Assert.assertEquals("Hola", viewModel.searchText.value)
    }

    @Test
    fun testPerformSearch_withNonEmptyText() {
        viewModel.setSearchText("Kotlin")
        viewModel.performSearch()
        Assert.assertEquals(true, viewModel.showSnackbar.value)
    }

    @Test
    fun testPerformSearch_withEmptyText() {
        viewModel.setSearchText("")
        viewModel.performSearch()
        Assert.assertEquals(false, viewModel.showSnackbar.value)

        viewModel.setSearchText("   ")
        viewModel.performSearch()
        Assert.assertEquals(false, viewModel.showSnackbar.value)
    }

    @Test
    fun testToggle() {
        viewModel.toggle()
        Assert.assertEquals(true, viewModel.toggleState.value)
        viewModel.toggle()
        Assert.assertEquals(false, viewModel.toggleState.value)
    }
}