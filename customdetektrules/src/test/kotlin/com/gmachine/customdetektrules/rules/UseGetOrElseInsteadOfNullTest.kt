package com.gmachine.customdetektrules.rules

import io.gitlab.arturbosch.detekt.rules.KotlinCoreEnvironmentTest
import io.gitlab.arturbosch.detekt.test.compileAndLintWithContext
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCoreEnvironment
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


@KotlinCoreEnvironmentTest
@Suppress("UnusedPrivateProperty")
class UseGetOrElseInsteadOfNullTest(private val env: KotlinCoreEnvironment) {

    private val kotlinCodeGetOrNull = """   
            fun x() {
                return runCatching{ }.getOrNull()
            }
          """.trimIndent()

    private val kotlinCodeGetOrNullVariable = """     
            val x = runCatching {  }.getOrNull()
          """.trimIndent()

    private val kotlinCodeGetOrElse = """
             fun x() {
                return runCatching {  }.getOrElse{ }
            }
          """.trimIndent()

    private val kotlinCodeGetOrElseVariable = """     
            val x = runCatching {  }.getOrElse{ }
          """.trimIndent()


    private val rule = UseGetOrElseInsteadOfGetOrNull()

    @Test
    fun `WHEN getOrNull called for kotlinResult SHOULD NOT pass detekt check`() {
        assertTrue(
            rule
                .compileAndLintWithContext(
                    env,
                    kotlinCodeGetOrNull
                )
                .isNotEmpty()
        )
    }

    @Test
    fun `WHEN getOrNull called via variable for kotlinResult SHOULD NOT pass detekt check`() {
        assertTrue(
            rule
                .compileAndLintWithContext(
                    env,
                    kotlinCodeGetOrNullVariable
                )
                .isNotEmpty()
        )
    }

    @Test
    fun `WHEN getOrElse called for kotlinResult SHOULD pass detekt check`() {
        assertTrue(
            rule
                .compileAndLintWithContext(
                    env,
                    kotlinCodeGetOrElse
                )
                .isEmpty()
        )
    }

    @Test
    fun `WHEN getOrElse called via variable for kotlinResult SHOULD NOT pass detekt check`() {
        assertTrue(
            rule
                .compileAndLintWithContext(
                    env,
                    kotlinCodeGetOrElseVariable
                )
                .isEmpty()
        )
    }
}