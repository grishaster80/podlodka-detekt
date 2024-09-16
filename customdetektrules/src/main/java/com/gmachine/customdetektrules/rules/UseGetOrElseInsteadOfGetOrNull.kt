package com.gmachine.customdetektrules.rules

import com.gmachine.customdetektrules.utils.isTypeResolutionEnabled
import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import io.gitlab.arturbosch.detekt.api.internal.RequiresTypeResolution
import io.gitlab.arturbosch.detekt.rules.fqNameOrNull
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression
import org.jetbrains.kotlin.psi.KtExpression
import org.jetbrains.kotlin.psi.psiUtil.getQualifiedExpressionForReceiver
import org.jetbrains.kotlin.resolve.calls.util.getType

@RequiresTypeResolution
internal class UseGetOrElseInsteadOfGetOrNull(config: Config = Config.empty) : Rule(config) {
    override val issue: Issue =
        Issue(javaClass.simpleName, Severity.Minor, ISSUE_DESCRIPTION, Debt.FIVE_MINS)

    override fun visitDotQualifiedExpression(expression: KtDotQualifiedExpression) {
        super.visitDotQualifiedExpression(expression)

        if (!isTypeResolutionEnabled()) {
            return
        }

        if (expression
                .selectorExpression
                .isGetOrNullCalled() &&
            expression
                .receiverExpression
                .isExpressionCalledForKotlinResult()
        ) {
            report(
                CodeSmell(
                    issue,
                    Entity.from(expression),
                    REPORT_MESSAGE
                )
            )
        }
    }

    private fun KtExpression?.isGetOrNullCalled(): Boolean =
        this?.text == "getOrNull()"

    private fun KtExpression?.isExpressionCalledForKotlinResult(): Boolean =
        this?.getType(bindingContext)?.fqNameOrNull().toString() == KOTLIN_RESULT_FQ_NAME


    companion object {
        private const val KOTLIN_RESULT_FQ_NAME = "kotlin.Result"

        private const val ISSUE_DESCRIPTION =
            "Due to this rule - You have to use getOrElse instead of getOrNull while working with kotlin.Result"

        private const val REPORT_MESSAGE =
            "getOrNull() called for kotlin.Result. Sealed class/object recommended instead of null"
    }
}