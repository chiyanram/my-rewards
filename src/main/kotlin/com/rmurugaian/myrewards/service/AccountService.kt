package com.rmurugaian.myrewards.service

import com.rmurugaian.myrewards.dto.AccountDTO
import com.rmurugaian.myrewards.dto.CreateAccountRequest

interface AccountService {

    fun save(createAccountRequest: CreateAccountRequest): AccountDTO

    fun accountsByName(userName: String): List<AccountDTO>
}