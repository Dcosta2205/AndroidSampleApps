package com.xyz.coroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

/**
 * @author Lloyd D'costa
 * This activity demonstrates how to get started with co routines
 */
class MainActivity : AppCompatActivity() {

    private val RESULT_1 = "Result #1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnClickMe.setOnClickListener {
            CoroutineScope(IO).launch {
                //This will run in a background thread
                callFakeApi()
            }
        }
    }

    private suspend fun callFakeApi() {
        setTextOnMainThread(getResultOneFromAPI())
    }

    /**
     * This method will set the textview on the main thread and its called from a background thread
     */
    private fun setTextOnMainThread(result: String) {
        CoroutineScope(Main).launch {
            //This will be called in the main Thread
            val text = tvResult.text.toString() + "\n $result"
            tvResult.text = text
        }
    }

    private suspend fun getResultOneFromAPI(): String {
        printLogs("getResultOneFromAPI")
        return RESULT_1
    }

    private suspend fun printLogs(methodName: String) {
        println("Debug :Method name is $methodName and Thread name is ${Thread.currentThread().name}")
    }

}