package com.rmurugaian.myrewards.controller

import com.rmurugaian.myrewards.dto.CreateAccountResponse
import com.rmurugaian.myrewards.dto.CreateAccountRequest
import com.rmurugaian.myrewards.service.AccountService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class AccountRestController(val accountService: AccountService) {

    @PostMapping
    fun save(@RequestBody createAccountDTO: CreateAccountRequest): CreateAccountResponse {
        return accountService.save(createAccountDTO)
    }

}