package com.viniciuspiih.calculadoradeimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private val messageTextView: TextView by lazy { text_result_message }
    private val resultTextView: TextView by lazy { text_result }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.imc.observe(this, Observer {
            resultTextView.text = getString(R.string.format).format(it)
            resultTextView.visibility = View.VISIBLE
        })

        viewModel.imcResult.observe(this, Observer {
            messageTextView.text = it
            messageTextView.visibility = View.VISIBLE
        })

        button_calculate.setOnClickListener {
            if (validationOk()) {
                val weight = edit_weight.text.toString().toFloat()
                val height = edit_height.text.toString().toFloat()

                viewModel.calculateIMC(weight, height, this)
            } else {
                Toast.makeText(applicationContext, getString(R.string.empty_fields), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun validationOk(): Boolean {
        return (edit_weight.text.toString() != "" && edit_height.text.toString() != "")
    }

}

