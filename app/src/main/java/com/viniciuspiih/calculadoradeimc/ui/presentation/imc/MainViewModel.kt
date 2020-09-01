package com.viniciuspiih.calculadoradeimc.ui.presentation.imc

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viniciuspiih.calculadoradeimc.R

class MainViewModel : ViewModel() {

    private var _imc = MutableLiveData<Float>()
    val imc: LiveData<Float> = _imc

    private var _imcResult = MutableLiveData<Int>()
    val imcResult: LiveData<Int> = _imcResult

    private var _showMessage = MutableLiveData<Int>()
    val showMessage: LiveData<Int> = _showMessage

    private var _showResult = MutableLiveData<Boolean>()
    val showResult: LiveData<Boolean> = _showResult

    private fun calculateIMC(weight: Float, height: Float) {
        val result = (weight / (height * height))

        when {
            result <= 17 -> _imcResult.value = R.string.very_low
            result <= 18.49 -> _imcResult.value = R.string.below
            result <= 24.99 -> _imcResult.value = R.string.normal
            result <= 29.99 -> _imcResult.value = R.string.above
            result <= 34.99 -> _imcResult.value = R.string.obesity_one
            result <= 39.99 -> _imcResult.value = R.string.obesity_two
            else -> _imcResult.value = R.string.obesity_three
        }

        _imc.value = result
        _showResult.value = true
    }

    private fun validationOk(weight: String, height: String) =
        weight.isNotEmpty() && height.isNotEmpty()

    fun onButtonClick(weight: String, height: String) {
        if (validationOk(weight, height)) {
            calculateIMC(weight.toFloat(), height.toFloat())
        } else {
            _showMessage.value = R.string.empty_fields
        }
    }

}