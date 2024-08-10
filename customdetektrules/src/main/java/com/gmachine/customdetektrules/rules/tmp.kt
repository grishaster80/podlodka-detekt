package com.gmachine.customdetektrules.rules

fun tmp() {
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