package com.rmurugaian.myrewards.controller

import com.rmurugaian.myrewards.dto.AccountDTO
import com.rmurugaian.myrewards.service.AccountService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/accounts")
class AccountRestController(val accountService: AccountService) {

    @PostMapping
    fun save(@RequestBody accountDTO: AccountDTO): AccountDTO {
        return accountService.save(accountDTO)
    }

    @GetMapping
    fun accounts(): List<AccountDTO> {
        return accountService.accounts()
    }
}