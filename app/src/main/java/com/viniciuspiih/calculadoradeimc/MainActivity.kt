package com.viniciuspiih.calculadoradeimc

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private val messageTextView: TextView by lazy { text_result_message }
    private val resultTextView: TextView by lazy { text_result }
    private val calculateButton: Button by lazy { button_calculate }
    private val weightEditText: EditText by lazy { edit_weight }
    private val heightEditText: EditText by lazy { edit_height }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        observe()

        calculateButton.setOnClickListener {
            onCalculateIMC()
        }
    }

    private fun observe() {
        viewModel.imc.observe(this, Observer {
            resultTextView.text = getString(R.string.format).format(it)
            resultTextView.visibility = View.VISIBLE
        })

        viewModel.imcResult.observe(this, Observer {
            messageTextView.text = it
            messageTextView.visibility = View.VISIBLE
        })
    }

    private fun onCalculateIMC() {
        hideKeyboard(calculateButton)

        if (validationOk()) {
            val weight = weightEditText.text.toString().toFloat()
            val height = heightEditText.text.toString().toFloat()

            viewModel.calculateIMC(weight, height, this)

        } else {
            Toast.makeText(applicationContext,
                getString(R.string.empty_fields),
                Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun validationOk(): Boolean {
        return (weightEditText.text.toString() != "" && heightEditText.text.toString() != "")
    }

    private fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}

