package com.viniciuspiih.calculadoradeimc

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private var _imc = MutableLiveData<Float>()
    var imc: LiveData<Float> = _imc

    private var _imcResult = MutableLiveData<String>()
    var imcResult: LiveData<String> = _imcResult

    fun calculateIMC(weight: Float, height: Float, context: Context) {
        val result = (weight / (height * height))

        when {
            result <= 17 -> _imcResult.value = context.getString(R.string.very_low)
            result <= 18.49 -> _imcResult.value = context.getString(R.string.below)
            result <= 24.99 -> _imcResult.value = context.getString(R.string.normal)
            result <= 29.99 -> _imcResult.value = context.getString(R.string.above)
            result <= 34.99 -> _imcResult.value = context.getString(R.string.obesity_one)
            result <= 39.99 -> _imcResult.value = context.getString(R.string.obesity_two)
            else -> _imcResult.value = context.getString(R.string.obesity_three)
        }


        _imc.value = result
    }

}