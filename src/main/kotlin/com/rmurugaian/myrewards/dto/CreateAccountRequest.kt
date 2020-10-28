package com.rmurugaian.myrewards.dto

import com.github.pozo.KotlinBuilder

@KotlinBuilder
data class CreateAccountRequest(
        val accountIdentifier: String,
        val bank: String,
        val user: String
)