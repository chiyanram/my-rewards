package com.rmurugaian.myrewards.service

import com.rmurugaian.myrewards.dto.CreateAccountResponse
import com.rmurugaian.myrewards.dto.CreateAccountRequest

interface AccountService {

    fun save(createAccountRequest: CreateAccountRequest): CreateAccountResponse

    fun accountsByName(userName: String): List<CreateAccountResponse>
}