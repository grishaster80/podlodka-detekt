package com.gmachine.customdetektrules.rules

import io.github.detekt.test.utils.compileContentForTest
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.rules.KotlinCoreEnvironmentTest
import io.gitlab.arturbosch.detekt.test.lint
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

@KotlinCoreEnvironmentTest
class FunctionNameTooShortTest {
    private val subject = FunctionNameTooShort(Config.empty)

    private val kotlinCodeWithShortFunction = """     
            fun f() {
                println("function called")
            }
            
            f()
          """.trimIndent()

    private val kotlinCodeWithValidFunction = """     
            fun myFun() {
                println("function called")
            }
            
            myFun()
          """.trimIndent()


    @Test
    fun `WHEN function name long enough SHOULD pass detekt check`() {
        val ktFile = compileContentForTest(
            content = kotlinCodeWithValidFunction
        )

        assertTrue(
            subject.lint(ktFile)
                .isEmpty()
        )
    }

    @Test
    fun `WHEN function name not long enough SHOULD NOT pass detekt check`() {
        val ktFile = compileContentForTest(
            content = kotlinCodeWithShortFunction
        )

        assertTrue(
            subject.lint(ktFile)
                .isNotEmpty()
        )
    }
}