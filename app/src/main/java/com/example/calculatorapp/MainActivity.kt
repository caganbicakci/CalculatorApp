package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private var resultTextView: TextView? = null
    private var tempResult: Double? = 0.0
    private var currentOperatorBtn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultText)
        resultTextView?.text = ""
    }

    fun onDigitClicked(view: View) {
        val button = view as Button

        if (button.text == ".") {
            if (!resultTextView!!.text.contains(".")) {
                resultTextView?.append(button.text)
            }
        } else {
            resultTextView?.append(button.text)
        }

    }

    fun onOperatorClicked(view: View) {
        val operator = view as Button
        when (operator.text) {
            "AC" -> clearResult()
            "DEL" -> deleteLastInput()
            "-" -> subtractOperation()
            "+" -> additionOperation()
            "=" -> equalsOperation()
            "*" -> multiplyOperation()
            "/" -> divideOperation()
        }
    }

    private fun clearResult() {
        resultTextView!!.text = ""
        tempResult = 0.0
    }

    private fun deleteLastInput() {
        resultTextView?.text = resultTextView!!.text.dropLast(1)
    }

    private fun subtractOperation() {
        if (resultTextView!!.text.isNullOrEmpty()) {
            resultTextView?.append("-")
        } else {
            if (!resultTextView!!.text.startsWith("-")) {
                tempResult = resultTextView?.text.toString().toDouble()
                resultTextView?.text = ""

                currentOperatorBtn = findViewById(R.id.btnMinus)
                currentOperatorBtn!!.setBackgroundColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.orangeGrey
                    )
                )
            }
        }
    }

    private fun additionOperation() {
        tempResult = resultTextView?.text.toString().toDouble()
        resultTextView?.text = ""

        currentOperatorBtn = findViewById(R.id.btnPlus)
        currentOperatorBtn!!.setBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.orangeGrey
            )
        )
    }

    private fun equalsOperation() {

        val current: Double = if (!resultTextView?.text.isNullOrEmpty()) {
            resultTextView?.text.toString().toDouble()
        } else {
            0.0
        }

        when (currentOperatorBtn?.text) {
            "+" -> resultTextView?.text = (tempResult?.plus(current)).toString()
            "-" -> resultTextView?.text = (tempResult?.minus(current)).toString()
            "*" -> {
                val result1 = tempResult
                val result2 = resultTextView?.text.toString().toDouble()

                if (result1 != null) {
                    resultTextView?.text = (result1 * result2).toString()
                } else {
                    resultTextView?.text = "0.0"
                }
            }
            "/" -> {
                if (current == 0.0 || current.isNaN()) {
                    resultTextView?.text = ""
                    Toast.makeText(this, "Can not divide by zero!", Toast.LENGTH_SHORT).show()
                } else {
                    resultTextView?.text = (tempResult?.div(current)).toString()
                }

            }

        }
        resetOperatorButton()
    }

    private fun multiplyOperation() {

        if (!resultTextView?.text.isNullOrEmpty()) {
            tempResult = resultTextView?.text.toString().toDouble()
        }

        resultTextView?.text = ""

        currentOperatorBtn = findViewById(R.id.btnMultiply)
        currentOperatorBtn?.setBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.orangeGrey
            )
        )
    }

    private fun divideOperation() {
        tempResult = resultTextView?.text.toString().toDouble()
        resultTextView?.text = ""

        currentOperatorBtn = findViewById(R.id.btnDivide)
        currentOperatorBtn?.setBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.orangeGrey
            )
        )
    }

    private fun resetOperatorButton() {
        currentOperatorBtn?.setBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.orange
            )
        )
    }

}