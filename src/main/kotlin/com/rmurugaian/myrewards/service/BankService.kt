package com.rmurugaian.myrewards.service

import com.rmurugaian.myrewards.dto.BankDTO

interface BankService {

    fun save(bankDTO: BankDTO): BankDTO

    fun all(): List<BankDTO>
}