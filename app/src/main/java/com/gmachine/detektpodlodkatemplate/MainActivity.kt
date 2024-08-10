package com.gmachine.detektpodlodkatemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val computationResultNullable = someOperationResult().getOrNull()

        val computationResultNonNullable = someOperationResult().getOrElse {
            OperationResult.FAILURE
        }

        println(computationResultNullable)

        println(computationResultNonNullable)
    }

    private fun someOperationResult(): Result<OperationResult> {
        return Result.failure(RuntimeException())
    }

    private enum class OperationResult {
        SUCCESS, FAILURE
    }

    private fun x() {}
}
