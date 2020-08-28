package com.viniciuspiih.calculadoradeimc

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var mImc = MutableLiveData<Float>()
    var imc = mImc

    private var mImcResult = MutableLiveData<String>()
    var imcResult = mImcResult

    fun calculateIMC(weight: Float, height: Float) {
        val result = (weight / (height * height))

        when {
            result <= 17 -> imcResult.value = "Muito abaixo do Peso!"
            result <= 18.49 -> imcResult.value = "Abaixo do Peso!"
            result <= 24.99 -> imcResult.value = "Peso Normal!"
            result <= 29.99 -> imcResult.value = "Acima do Peso!"
            result <= 34.99 -> imcResult.value = "Obesidade I"
            result <= 39.99 -> imcResult.value = "Obesidade II"
            else -> imcResult.value = "Obesidade III"
        }


        mImc.value = result
    }

}