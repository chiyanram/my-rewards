package com.rmurugaian.myrewards.domain

import com.github.pozo.KotlinBuilder
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@KotlinBuilder
data class UserActivity(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        val id: Long?,
        @ManyToOne
        val rewardsUser: RewardsUser,
        val type: Activity,
        val description: String,
        val activityDate: LocalDateTime
)