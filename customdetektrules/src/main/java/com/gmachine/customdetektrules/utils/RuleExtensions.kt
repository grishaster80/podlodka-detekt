package com.gmachine.customdetektrules.utils

import io.gitlab.arturbosch.detekt.api.Rule
import org.jetbrains.kotlin.resolve.BindingContext

internal fun Rule.isTypeResolutionEnabled() = bindingContext != BindingContext.EMPTY