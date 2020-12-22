package com.example.calculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ArithmeticException
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    var powerState: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn0.setOnClickListener(clickListener)
        btn1.setOnClickListener(clickListener)
        btn2.setOnClickListener(clickListener)
        btn3.setOnClickListener(clickListener)
        btn4.setOnClickListener(clickListener)
        btn5.setOnClickListener(clickListener)
        btn6.setOnClickListener(clickListener)
        btn7.setOnClickListener(clickListener)
        btn8.setOnClickListener(clickListener)
        btn9.setOnClickListener(clickListener)
        btnAdd.setOnClickListener(clickListener)
        btnSubtract.setOnClickListener(clickListener)
        btnDecimal.setOnClickListener(clickListener)
        btnDivide.setOnClickListener(clickListener)
        btnClear.setOnClickListener(clickListener)
        btnModulo.setOnClickListener(clickListener)
        btnMultiply.setOnClickListener(clickListener)
        btnOnOff.setOnClickListener(clickListener)
        btnEquals.setOnClickListener(clickListener)
        btnBack.setOnClickListener(clickListener)
    }

    private val clickListener = View.OnClickListener {

        if (it.id != R.id.btnOnOff && !powerState) {
            Toast.makeText(this, "Please turn on the calculator!", Toast.LENGTH_SHORT).show()
            return@OnClickListener
        }
        var input: String = inputEditText.text.toString()

        when (it.id) {
            R.id.btn0 -> input += "0"
            R.id.btn1 -> input += "1"
            R.id.btn2 -> input += "2"
            R.id.btn3 -> input += "3"
            R.id.btn4 -> input += "4"
            R.id.btn5 -> input += "5"
            R.id.btn6 -> input += "6"
            R.id.btn7 -> input += "7"
            R.id.btn8 -> input += "8"
            R.id.btn9 -> input += "9"
            R.id.btnAdd -> input += "+"
            R.id.btnSubtract -> input += "-"
            R.id.btnMultiply -> input += "*"
            R.id.btnDivide -> input += "/"
            R.id.btnModulo -> input += "%"
            R.id.btnDecimal -> input += "."
            R.id.btnOnOff -> {
                power()
                input = ""
            }
            R.id.btnClear -> input = ""
            R.id.btnEquals -> if(!input.isEmpty()){
                try {
                    for (i in input.indices) {
                        if(i!=0){
                        if (input[i] == '*' || input[i] == '+' || input[i] == '-' || input[i] == '/' || input[i] == '%') {
                            val exp1: Double = input.substring(0, i).toDouble()
                            val exp2: Double = input.substring(i + 1, input.length).toDouble()
                            val answer = calculate(exp1, exp2, input[i])
                            if(answer.equals("Infinity")||answer.equals("-Infinity"))throw ArithmeticException()
                            input = answer.toString()
                            break
                        }
                    }
                    }
                } catch (ae: ArithmeticException) {
                    Toast.makeText(this, "Division by zero not possible!", Toast.LENGTH_SHORT)
                        .show()
                } catch (e: Exception) {
                    Toast.makeText(this, "Wrong input!", Toast.LENGTH_SHORT).show()
                }
            }
            R.id.btnBack -> if(!input.isEmpty())input = input.substring(0, input.length - 1)
        }
        inputEditText.setText(input)
    }

    private fun calculate(exp1: Double, exp2: Double, operator: Char): String {
        var result: String? = null
        when (operator) {
            '+' -> result = "${exp1 + exp2}"
            '-' -> result = "${exp1 - exp2}"
            '*' -> result = "${exp1 * exp2}"
            '/' -> result = "${exp1 / exp2}"
            '%' -> result = "${exp1 % exp2}"
        }
        return result!!
    }

    fun power() {
        if (!powerState) btnOnOff.setBackgroundColor(Color.parseColor("#FB0000"))
        else btnOnOff.setBackgroundColor(Color.parseColor("#46EF08"))
        powerState = !powerState

    }
}