package com.rmurugaian.myrewards.dto

import com.github.pozo.KotlinBuilder

@KotlinBuilder
data class CreateRewardsUserRequest(
        val userName: String,
        val firstName: String,
        val lastName: String
)