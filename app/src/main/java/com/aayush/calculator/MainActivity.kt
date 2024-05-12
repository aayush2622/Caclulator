package com.aayush.calculator

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updateLayoutParams
import com.aayush.calculator.databinding.ActivityMainBinding
import com.aayush.calculator.saving.PrefManager
import com.aayush.calculator.settings.SettingsActivity
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PrefManager.init(this)
        ThemeManager.apply(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val context = this
        binding.apply{
            root.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = statusBarHeight
                bottomMargin = navBarHeight
            }
            menuButton.setOnClickListener {
                startActivity(Intent(context, SettingsActivity::class.java))
            }
            clearButton.setOnClickListener {
                input.text = ""
                output.text = ""
            }

            removeButton.setOnClickListener {
                val text = input.text.toString()
                if (text.isNotEmpty()) {
                    input.text = text.substring(0, text.length - 1)
                }
                output.text = ""
            }

            zeroButton.setOnClickListener {
                input.text = addToInputText("0")
            }
            oneButton.setOnClickListener {
                input.text = addToInputText("1")
            }
            twoButton.setOnClickListener {
                input.text = addToInputText("2")
            }
            threeButton.setOnClickListener {
                input.text = addToInputText("3")
            }
            fourButton.setOnClickListener {
                input.text = addToInputText("4")
            }
            fiveButton.setOnClickListener {
                input.text = addToInputText("5")
            }
            sixButton.setOnClickListener {
                input.text = addToInputText("6")
            }
            sevenButton.setOnClickListener {
                input.text = addToInputText("7")
            }
            eightButton.setOnClickListener {
                input.text = addToInputText("8")
            }
            nineButton.setOnClickListener {
                input.text = addToInputText("9")
            }
            decimalButton.setOnClickListener {
                input.text = addToInputText(".")
            }
            plusButton.setOnClickListener {
                input.text = addToInputText("+")
            }
            subtractButton.setOnClickListener {
                input.text = addToInputText("-")
            }
            multiplyButton.setOnClickListener {
                input.text = addToInputText("*")
            }
            divideButton.setOnClickListener {
                input.text = addToInputText("/")
            }
            parenthesesButton.setOnClickListener {
                val text = input.text.toString()
                if (text.isEmpty() || text.last() == '(' || text.last() == '+' || text.last() == '-' || text.last() == '*' || text.last() == '/') {
                    input.text = addToInputText("(")
                } else {
                    input.text = addToInputText(")")
                }
            }
            equalButton.setOnClickListener {
                showResult()
            }

        }
    }
    private fun addToInputText(buttonValue: String): String {

        return binding.input.text.toString() + "" + buttonValue
    }
    private fun getInputExpression(): String {
        var expression = binding.input.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }
    private fun showResult() {
        val input = getInputExpression()
        val expression = Expression(input)
        val result = expression.calculate()
        val toInt = if (result % 1 == 0.0) {
            result.toInt()
        } else {
            result
        }
        binding.output.text = toInt.toString()
        val currentHistory =  PrefManager.getCustomVal<Set<String>>("history", setOf()).plus(input)
        PrefManager.setCustomVal("history", currentHistory)
    }
}

