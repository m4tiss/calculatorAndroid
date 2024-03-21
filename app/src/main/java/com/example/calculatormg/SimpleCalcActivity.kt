package com.example.calculatormg

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder



class SimpleCalcActivity : Activity() {


    private var operationsText: String = ""
    private var resultText: String = ""

    private var canClickNumber: Boolean = true
    private var canClickOperation: Boolean = false
    private var canClickComma: Boolean = false
    private var isComma: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simple_calc_layout)

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
            result.text = calculatedResult.toString()
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
        }
    }

    fun changeSign(view: View) {
        val operations = findViewById<TextView>(R.id.operationsArea)
        val currentText = operations.text.toString()

        if (currentText.isNotEmpty() && currentText != "0") {
            val newText = if (currentText.startsWith("-")) {
                currentText.substring(1)
            } else {
                "-$currentText"
            }
            operations.text = newText
            operationsText = newText
        }
    }

}