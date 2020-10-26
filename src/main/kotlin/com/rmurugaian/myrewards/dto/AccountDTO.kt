package com.rmurugaian.myrewards.dto

import com.github.pozo.KotlinBuilder
import java.math.BigDecimal

@KotlinBuilder
data class AccountDTO(
        val accountIdentifier: String,
        val points: Long?,
        val cash: BigDecimal?,
        val bank: String,
        val user: String)
