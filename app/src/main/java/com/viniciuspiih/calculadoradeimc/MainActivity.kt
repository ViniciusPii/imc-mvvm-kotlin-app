package com.viniciuspiih.calculadoradeimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.imc.observe(this, Observer {
            text_result.text = "%.2f".format(it)
            text_result.visibility = View.VISIBLE
        })

        viewModel.imcResult.observe(this, Observer {
            text_result_message.text = it
            text_result_message.visibility = View.VISIBLE
        })

        button_calculate.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val id = v?.id
        if (id == R.id.button_calculate) {
            calculateImc()
        }
    }

    private fun calculateImc() {
        val weight = edit_weight.text.toString().toFloat()
        val height = edit_height.text.toString().toFloat()

        viewModel.calculateIMC(weight, height)
    }


}