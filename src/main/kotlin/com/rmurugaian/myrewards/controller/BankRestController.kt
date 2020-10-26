package com.rmurugaian.myrewards.controller

import com.rmurugaian.myrewards.dto.BankDTO
import com.rmurugaian.myrewards.service.BankService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/banks")
class BankRestController(val bankService: BankService) {

    @PostMapping
    fun save(@RequestBody bankDTO: BankDTO): BankDTO {
        return bankService.save(bankDTO)
    }

    @GetMapping
    fun all(): List<BankDTO> {
        return bankService.all()
    }
}