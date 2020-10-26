package com.rmurugaian.myrewards.repository

import com.rmurugaian.myrewards.domain.Bank
import org.springframework.data.jpa.repository.JpaRepository

interface BankRepository : JpaRepository<Bank, Long> {
    fun findByName(name: String): Bank
}