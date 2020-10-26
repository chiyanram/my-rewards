package com.rmurugaian.myrewards.dto

import com.github.pozo.KotlinBuilder
import java.math.BigDecimal

@KotlinBuilder
data class RewardsUserDTO(
        val userName: String?,
        val firstName: String,
        val lastName: String,
        val totalPoints: Long?,
        val totalCash: BigDecimal?) {

    val accounts: List<AccountDTO> = ArrayList()
}