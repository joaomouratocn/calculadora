package br.com.programadorjm.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.programadorjm.calculator.KeyValues.*
import br.com.programadorjm.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    private lateinit var input:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnZero.setOnClickListener { processClick(ZERO) }
        binding.btnOne.setOnClickListener { processClick(ONE) }
        binding.btnTwo.setOnClickListener { processClick(TWO) }
        binding.btnThree.setOnClickListener { processClick(THREE) }
        binding.btnFour.setOnClickListener { processClick(FOUR) }
        binding.btnFive.setOnClickListener { processClick(FIVE) }
        binding.btnSix.setOnClickListener { processClick(SIX) }
        binding.btnSeven.setOnClickListener { processClick(SEVEN) }
        binding.btnEight.setOnClickListener { processClick(EIGHT) }
        binding.btnNine.setOnClickListener { processClick(NINE) }

        binding.btnAc.setOnClickListener{ processClick(AC) }
        binding.btnBackspace.setOnClickListener{ processClick(BS) }
        binding.btnDivide.setOnClickListener{ processClick(DIVIDE) }
        binding.btnMultiply.setOnClickListener{ processClick(MULTIPLY) }
        binding.btnSubtract.setOnClickListener{ processClick(SUBTRACT) }
        binding.btnSum.setOnClickListener{ processClick(SUM) }
        binding.btnDot.setOnClickListener{ processClick(DOT) }
        binding.btnEqual.setOnClickListener{ processClick(EQUAL) }

        resetInput()
    }

    //Atualizar calculo e display
    fun processClick(key:KeyValues){
        when(key) {
            EQUAL -> makeCalc()
            AC -> resetInput()
            DIVIDE -> setSignalAndDot(DIVIDE)
            MULTIPLY -> setSignalAndDot(MULTIPLY)
            SUM -> setSignalAndDot(SUM)
            SUBTRACT -> setSignalAndDot(SUBTRACT)
            DOT -> setSignalAndDot(DOT)
            BS -> {
                if (input.length <= 1){ resetInput() }
                else{ input = input.dropLast(1) }
            }
            else -> input += key.value
        }
        updateDisplay(input)
    }

    //Realizar calculo
    private fun makeCalc(){
        if(input != ""){
            val inputReplace = input.replace("×", "*", false).replace("÷", "/", false)
            val expression = ExpressionBuilder(inputReplace).build()
            input = expression.evaluate().toString()
            updateDisplay(input)
        }
    }

    //resetar calculadora
    private fun resetInput(){
        input = ""
        binding.txtDisplay.text = input
    }

    //Atualiza o textView Display
    private fun updateDisplay(value:String){
        binding.txtDisplay.text = value
    }

    //valida e set senais e ponto
    private fun setSignalAndDot(key:KeyValues){
        if(key == DOT) {
            if (input == "") input = "0."
            else if (input[input.length - 1].isDigit()) input += key.value
        }else {
            if (input[input.length - 1].isDigit()) input += key.value
            else input = input.dropLast(1)+key.value
        }

    }
}