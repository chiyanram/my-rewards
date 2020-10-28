package com.rmurugaian.myrewards.dto

import com.github.pozo.KotlinBuilder
import com.rmurugaian.myrewards.exception.InvalidInputException
import java.math.BigDecimal

@KotlinBuilder
data class BankDTO(val name: String, val conversionRate: BigDecimal) {
    init {
        if (conversionRate.compareTo(BigDecimal.ZERO) == 0) {
            throw InvalidInputException("conversion rate is mandatory")
        }

        if (conversionRate.compareTo(BigDecimal(0.1)) == -1) {
            throw InvalidInputException("conversion rate must be greater than 0.1")
        }

        if (conversionRate.compareTo(BigDecimal(0.3)) == 1) {
            throw InvalidInputException("conversion rate must be less than 0.3")
        }
    }
}