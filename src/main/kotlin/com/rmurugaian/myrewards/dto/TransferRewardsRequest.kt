package com.rmurugaian.myrewards.dto

data class TransferRewardsRequest(
        val userName: String,
        val totalPoints: Long,
        val description: String,
        val toAccount: TransferAccount
)