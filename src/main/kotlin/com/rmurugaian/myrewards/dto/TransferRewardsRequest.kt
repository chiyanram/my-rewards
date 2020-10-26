package com.rmurugaian.myrewards.dto

data class TransferRewardsRequest(
        val userName: String,
        val fromAccounts: List<TransferAccount>,
        val toAccount: TransferAccount
)