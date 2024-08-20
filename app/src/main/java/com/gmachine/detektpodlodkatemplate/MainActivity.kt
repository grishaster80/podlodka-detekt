package com.gmachine.detektpodlodkatemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println(42)
        println(getNumber()?.toString())

        println(OperationResult.values().first())

        val x = runCatching {  }.getOrNull()

        runCatching {  }.getOrNull()
    }

    fun getNumber(): Int {
        return 42
    }

    private fun someOperationResult(): Result<OperationResult> {
        return Result.failure(RuntimeException())
    }

    private enum class OperationResult {
        SUCCESS, FAILURE
    }

    private fun x() {}
}
