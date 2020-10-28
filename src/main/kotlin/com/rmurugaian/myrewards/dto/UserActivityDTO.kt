package com.rmurugaian.myrewards.dto

import com.github.pozo.KotlinBuilder
import com.rmurugaian.myrewards.domain.Activity
import java.time.LocalDateTime

@KotlinBuilder
data class UserActivityDTO(
        val userName: String,
        val type: Activity,
        val description: String,
        val activityDate: LocalDateTime)