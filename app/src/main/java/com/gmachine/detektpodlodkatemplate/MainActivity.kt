package com.gmachine.detektpodlodkatemplate

import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        println(42)
        println(getNumber()?.toString())

        println(OperationResult.values().first())


        val expressionResultNullable = runCatching { getExpressionResult() }
            .getOrNull()

        val expressionResult = runCatching { getExpressionResult() }
            .getOrElse {
                ExpressionResult.Failure
            }

        println(expressionResultNullable)

        println(expressionResult)
    }

    sealed interface ExpressionResult {
        data class Value(val counter: Int) : ExpressionResult

        data object Failure : ExpressionResult
    }

    fun getExpressionResult(): ExpressionResult {
        return ExpressionResult.Value(3)
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
