package com.example.maury.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Numbers
        //calling a function of expressionAppend when a number is clicked and passing number value as string along with boolean value
        num1.setOnClickListener{expressionAppend("1",true)}
        num2.setOnClickListener{expressionAppend("2",true)}
        num3.setOnClickListener{expressionAppend("3",true)}
        num4.setOnClickListener{expressionAppend("4",true)}
        num5.setOnClickListener{expressionAppend("5",true)}
        num6.setOnClickListener{expressionAppend("6",true)}
        num7.setOnClickListener{expressionAppend("7",true)}
        num8.setOnClickListener{expressionAppend("8",true)}
        num9.setOnClickListener{expressionAppend("9",true)}
        num0.setOnClickListener{expressionAppend("0",true)}
        Dot. setOnClickListener{expressionAppend(".",true)}
        numPI.setOnClickListener{expressionAppend("3.14159265358979",true)}
        numE.setOnClickListener{expressionAppend("2.71828182846",true)}

        //operations
        //calling a function of expressionAppend when an operator is clicked and passing an operator as string along with boolean value
        operAdd.        setOnClickListener{expressionAppend("+",false)}
        operSubtract.   setOnClickListener{expressionAppend("-",false)}
        operMultiply.   setOnClickListener{expressionAppend("*",false)}
        operDivide.     setOnClickListener{expressionAppend("/",false)}
        bracketOpen.    setOnClickListener{expressionAppend("(",false)}
        bracketClose.   setOnClickListener{expressionAppend(")",false)}

        //Percentage Operation
        operPercent.setOnClickListener {
            val string=expression.text.toString()  //assigning expression to variable after converting into string
            if(string.isNotEmpty()){                // Performs operation if expression is not empty
                val str= string.last()

                //if block is written to check illogical expressions ending with operators
                if(str.equals('+')||str.equals('-')||str.equals('*')||str.equals('/')||str.equals('(')||str.equals(')')){
                    answer.text= ""
                }
                else {
                    // executes this block if expression is valid
                    try {
                        val exp = ExpressionBuilder(expression.text.toString()).build()     // building an expression which can be executed using internal functions
                        val result = exp.evaluate()
                        answer.text = (result / 100).toString()                             //assigning percentage value to answer
                    }
                    catch(e:Exception){
                        Log.d("Exception","message:"+ e.message)                       // handling the exceptions

                    }
                    }
            }
            else answer.text=0.toString()          //assigns answer to zero if expression is empty
        }
        operSign.setOnClickListener {
            val string=expression.text.toString()
            if(string.isNotEmpty()){
                val str= string.last()

                //if block is written to check illogical expressions ending with operators
                if(str.equals('+')||str.equals('-')||str.equals('*')||str.equals('/')||str.equals('(')||str.equals(')')){
                    answer.text= ""
                }
                else {
                    try{                                                                // executes this block if expression is valid
                    val exp = ExpressionBuilder(expression.text.toString()).build()     // building an expression which can be executed using internal functions
                    val result    = exp.evaluate()
                    answer.text   = ((0-result)).toString()}                             //assigning signed changed value to answer
                    catch(e:Exception){
                        Log.d("Exception","message:"+ e.message)                        // handling the exceptions

                    }
                }
            }
            else answer.text=0.toString()          //assigns answer to zero if expression is empty
        }

        //writing a function to clear both the text fields on clicking "C" icon
        operClear.setOnClickListener{
           expression.text=""
            answer.text=""
        }

        //writing a function to delete last entered text  on clicking "DEL" icon
        Back.setOnClickListener{
            val string=expression.text.toString()                         //an expression is copied to a constant variable
            if(string.isNotEmpty()){
                expression.text = string.substring(0,string.length-1)     //deleting the last entry using a internal function substring(start position,end position)
            }
            answer.text = ""                                              //clearing the evaluated result
        }

        // writing a function to validate the expression when "=" is  clicked
        operEqualsto.setOnClickListener{
            val string=answer.text.toString()
            if(answer.text.toString().isNotEmpty()) {expression.text= string}

            else{
                try {
                    val exp = ExpressionBuilder(expression.text.toString()).build() //copying the string by converting an expression using ExpressionBuilder function
                    val result = exp.evaluate()                                   //Evaluating the result
                    val longResult = result.toLong()                                        //toLong parses the string as a Long number and returns the result.
                    if (result == longResult.toDouble())                                     // if-else block is used to display result as double or longResult
                        answer.text = longResult.toString()
                    else
                        answer.text = result.toString()
                }
                catch(e:Exception){
                    Log.d("Exception","message:"+ e.message)                       // handling the exceptions

                }
            }
        }
    }


    fun expressionAppend(string:String,  i : Boolean){      //writing a function to append the latest entry to expression

        if (answer.text.isNotEmpty()){                      // Clearing the expression field if a result is to be used for further calculations
            expression.text=""
        }
        if (i){
            answer.text = ""
            expression.append(string)                       // used to append the numbers into the expression
        }
        else{
            expression.append(answer.text)                  // first  appending the result into expression and then appending the operator
            expression.append(string)
            answer.text = ""
        }

    }

}
