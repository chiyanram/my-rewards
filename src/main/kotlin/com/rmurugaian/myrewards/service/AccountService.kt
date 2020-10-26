package com.rmurugaian.myrewards.service

import com.rmurugaian.myrewards.dto.AccountDTO

interface AccountService {

    fun save(account: AccountDTO): AccountDTO

    fun accounts(): List<AccountDTO>
}