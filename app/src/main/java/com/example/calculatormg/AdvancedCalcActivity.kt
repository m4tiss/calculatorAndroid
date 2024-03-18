package com.example.calculatormg

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder



class AdvancedCalcActivity : Activity() {

    private var operationsText: String = ""
    private var resultText: String = ""

    private var canClickNumber: Boolean = true
    private var canClickOperation: Boolean = false
    private var canClickComma: Boolean = false
    private var isComma: Boolean = false
    private var canAdvancedOperationActivity: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.advanced_calc_layout)

        if (savedInstanceState != null) {
            operationsText = savedInstanceState.getString("operationsText", "")
            resultText = savedInstanceState.getString("resultText", "")

            val operations = findViewById<TextView>(R.id.operationsArea)
            operations.text = operationsText

            val result = findViewById<TextView>(R.id.resultArea)
            result.text = resultText
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("operationsText", operationsText)
        outState.putString("resultText", resultText)
        super.onSaveInstanceState(outState)
    }

    fun numberAction(view: View){
        val operations = findViewById<TextView>(R.id.operationsArea)
        val button = view as Button
        if(canClickNumber){
            operations.append(button.text.toString())
            operationsText = operations.text.toString()
            canClickOperation = true
            canClickComma = true
            canClickNumber = true
            canAdvancedOperationActivity = true
        }
    }


    fun advancedOperationAction(view: View) {
        if(canAdvancedOperationActivity){
            val operations = findViewById<TextView>(R.id.operationsArea)
            val button = view as Button
            val result = findViewById<TextView>(R.id.resultArea)
            val currentText = operations.text.toString()
            val number = currentText.toDouble()
            var currentExp = ""
            when (button.id ) {
                R.id.sqrt ->{
                    currentExp = "sqrt($number)"
                }
                R.id.sin ->{
                    currentExp = "sin($number)"
                }
                R.id.cos ->{
                    currentExp = "cos($number)"
                }
                R.id.tan ->{
                    currentExp = "tan($number)"
                }
                R.id.percent ->{
                    currentExp = "($number)*0.01"
                }
                R.id.power ->{
                    currentExp = "($number)^"
                }
                R.id.log ->{
                    currentExp = "log($number)"
                }
            }
            operations.text = currentExp
            operationsText = operations.text.toString()
        }
    }

    fun operationAction(view: View){
        val operations = findViewById<TextView>(R.id.operationsArea)
        val button = view as Button
        if(canClickOperation){
            operations.append(button.text.toString())
            operationsText = operations.text.toString()
            canClickOperation = false
            canClickComma = false
            canClickNumber = true
            isComma = false
            canAdvancedOperationActivity = false
        }
    }

    fun allClear(view: View) {
        val operations = findViewById<TextView>(R.id.operationsArea)
        operations.text = ""
        operationsText = operations.text.toString()

        val result = findViewById<TextView>(R.id.resultArea)
        result.text = ""
        resultText = result.text.toString()

        canClickOperation = false
        canClickComma = false
        canClickNumber = true
        isComma = false
        canAdvancedOperationActivity = false
    }

    fun resultAction(view: View) {
        calculateResult()
    }

    private fun calculateResult() {
        val operations = findViewById<TextView>(R.id.operationsArea)
        val result = findViewById<TextView>(R.id.resultArea)

        val expressionString = operations.text.toString()
        try {
            val expression = ExpressionBuilder(expressionString).build()
            val calculatedResult = expression.evaluate()
            val resultString = String.format("%.8f", calculatedResult)
            result.text = resultString.substring(0, minOf(resultString.length, 8))
            resultText = result.text.toString()
        } catch (e: Exception) {
            result.text = "Error"
            resultText = result.text.toString()
        }
    }

    fun backspaceAction(view: View) {
        val operations = findViewById<TextView>(R.id.operationsArea)
        val result = findViewById<TextView>(R.id.resultArea)

        val currentText = operations.text.toString()
        if (currentText.isNotEmpty()) {
            operations.text = currentText.substring(0, currentText.length - 1)
            operationsText = operations.text.toString()
        }

        result.text = ""
        resultText = result.text.toString()
    }

    fun commaAction(view: View) {
        val operations = findViewById<TextView>(R.id.operationsArea)
        val button = view as Button
        if(canClickComma && !isComma){
            operations.append(button.text.toString())
            operationsText = operations.text.toString()
            canClickOperation = false
            canClickComma = false
            canClickNumber = true
            isComma=true
            canAdvancedOperationActivity = false
        }
    }

}