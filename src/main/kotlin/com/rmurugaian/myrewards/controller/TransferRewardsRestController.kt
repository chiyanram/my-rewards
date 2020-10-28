package com.rmurugaian.myrewards.controller

import com.rmurugaian.myrewards.dto.AccountDTO
import com.rmurugaian.myrewards.dto.TransferRewardsRequest
import com.rmurugaian.myrewards.service.TransferRewardsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transfer-rewards")
class TransferRewardsRestController(
        val transferRewardsService: TransferRewardsService) {

    @PostMapping
    fun transfer(@RequestBody transferRewardsRequest: TransferRewardsRequest): List<AccountDTO> {

        return transferRewardsService.transfer(transferRewardsRequest)
    }

}