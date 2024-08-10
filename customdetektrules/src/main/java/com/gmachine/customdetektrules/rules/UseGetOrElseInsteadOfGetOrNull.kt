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
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression
import org.jetbrains.kotlin.psi.KtNamedFunction
import org.jetbrains.kotlin.resolve.descriptorUtil.fqNameSafe
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.typeUtil.supertypes

//private val kotlinResultFqName = FqName("kotlin.Result")

@RequiresTypeResolution
internal class UseGetOrElseInsteadOfGetOrNull(config: Config) : Rule(config) {
    override val issue: Issue =
        Issue(javaClass.simpleName, Severity.Minor, ISSUE_DESCRIPTION, Debt.FIVE_MINS)

    override fun visitClass(klass: KtClass) {
        super.visitClass(klass)

        report(
            CodeSmell(
                issue,
                Entity.from(klass),
                "UseGetOrElseInsteadOfGetOrNull visitClass isTypeResolutionEnabled ${isTypeResolutionEnabled()}"
            )
        )
    }

    override fun visitNamedFunction(function: KtNamedFunction) {
        super.visitNamedFunction(function)

        report(
            CodeSmell(
                issue,
                Entity.from(function),
                "UseGetOrElseInsteadOfGetOrNull visitFunction isTypeResolutionEnabled ${isTypeResolutionEnabled()}"
            )
        )
    }

    override fun visitDotQualifiedExpression(expression: KtDotQualifiedExpression) {
        super.visitDotQualifiedExpression(expression)

        report(
            CodeSmell(
                issue,
                Entity.from(expression),
                "UseGetOrElseInsteadOfGetOrNull visitDot isTypeResolutionEnabled ${isTypeResolutionEnabled()}"
            )
        )

        if (!isTypeResolutionEnabled()) {
            return
        }

        //val selectorExpression = expression.selectorExpression ?: return
        //val receiverExpression = expression.receiverExpression
    }

//    private fun isDotExpressionCalledForKotlinResultType(type: KotlinType): Boolean {
//        return type.supertypes().any { superType ->
//            superType.getClassFqName() == kotlinResultFqName
//        }
//    }

//    private fun KotlinType.getClassFqName(): FqName? {
//        return (constructor.declarationDescriptor as? ClassDescriptor)?.fqNameSafe
//    }


    companion object {
        private const val ISSUE_DESCRIPTION =
            "Due to this rule - You have to use getOrElse instead of getOrNull while working with kotlin.Result"
    }
}