package com.gmachine.customdetektrules.rules

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.psi.KtNamedFunction

class FunctionNameTooShort(config: Config) : Rule(config) {
    override val issue: Issue =
        Issue(javaClass.simpleName, Severity.Minor, ISSUE_DESCRIPTION, Debt.FIVE_MINS)

    override fun visitNamedFunction(function: KtNamedFunction) {
        function.name?.let { functionName ->
            if (functionName.isFunctionNameNotLongEnough()) {
                report(
                    CodeSmell(
                        issue,
                        Entity.from(function),
                        REPORT_MESSAGE
                    )
                )
            }
        }
    }

    private fun String.isFunctionNameNotLongEnough() : Boolean =
        this.length < MIN_FUNCTION_NAME_LENGTH

    companion object {
        private const val ISSUE_DESCRIPTION =
            "Due to this rule - You're not allowed to use function names shorter than 3 symbols"

        private const val REPORT_MESSAGE =
            "Function name is shorter than 3 symbols. For better readability consider using longer function name"

        private const val MIN_FUNCTION_NAME_LENGTH = 3
    }
}