package com.rmurugaian.myrewards.repository

import com.rmurugaian.myrewards.domain.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long> {
    @Suppress("FunctionName")
    fun findAllByUser_UserName(userName: String): List<Account>
}