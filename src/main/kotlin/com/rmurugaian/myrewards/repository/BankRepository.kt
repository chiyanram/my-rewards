package com.rmurugaian.myrewards.repository

import com.rmurugaian.myrewards.domain.Bank
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BankRepository : JpaRepository<Bank, Long> {
    fun findByName(name: String): Optional<Bank>
}