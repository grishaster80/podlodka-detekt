package com.gmachine.customdetektrules.provider

import com.gmachine.customdetektrules.rules.FunctionNameTooShort
import com.gmachine.customdetektrules.rules.UseEntriesInsteadOfValuesOnEnum
import com.gmachine.customdetektrules.rules.UseGetOrElseInsteadOfGetOrNull
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class CustomRulesProvider : RuleSetProvider {
    override val ruleSetId: String = "PodlodkaCustomRules"

    override fun instance(config: Config): RuleSet =
        RuleSet(
            id = ruleSetId,
            rules = listOf(
                FunctionNameTooShort(config),
                UseGetOrElseInsteadOfGetOrNull(config),
                UseEntriesInsteadOfValuesOnEnum(config)
            )
        )
}