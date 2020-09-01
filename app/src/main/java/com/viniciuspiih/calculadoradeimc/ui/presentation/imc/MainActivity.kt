package com.viniciuspiih.calculadoradeimc.ui.presentation.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.viniciuspiih.calculadoradeimc.R
import com.viniciuspiih.calculadoradeimc.utils.hideKeyboard
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    private val messageTextView: TextView by lazy { text_result_message }
    private val resultTextView: TextView by lazy { text_result }
    private val calculateButton: Button by lazy { button_calculate }
    private val weightEditText: EditText by lazy { edit_weight }
    private val heightEditText: EditText by lazy { edit_height }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        observes()
        listeners()
    }

    private fun observes() {
        viewModel.imc.observe(this, {
            resultTextView.text = getString(R.string.format).format(it)
        })

        viewModel.imcResult.observe(this, {
            messageTextView.setText(it)
        })

        viewModel.showMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.showResult.observe(this, ::onResult)
    }

    private fun listeners() {
        calculateButton.setOnClickListener {
            hideKeyboard()
            viewModel.onButtonClick(weightEditText.text.toString(), heightEditText.text.toString())
        }
    }

    private fun onResult(isVisible: Boolean) {
        messageTextView.isVisible = isVisible
        resultTextView.isVisible = isVisible
    }

}

