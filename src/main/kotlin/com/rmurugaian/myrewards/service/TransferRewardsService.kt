package com.rmurugaian.myrewards.service

import com.rmurugaian.myrewards.dto.AccountDTO
import com.rmurugaian.myrewards.dto.TransferRewardsRequest

interface TransferRewardsService {

    fun transfer(transferRewardsRequest: TransferRewardsRequest): List<AccountDTO>
}