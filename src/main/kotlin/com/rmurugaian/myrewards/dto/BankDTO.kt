package com.rmurugaian.myrewards.dto

import com.github.pozo.KotlinBuilder
import java.math.BigDecimal

@KotlinBuilder
data class BankDTO(val name: String, val conversionRate: BigDecimal)