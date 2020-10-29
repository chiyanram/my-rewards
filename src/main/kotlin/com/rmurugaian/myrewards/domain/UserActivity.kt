package com.rmurugaian.myrewards.domain

import com.github.pozo.KotlinBuilder
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@KotlinBuilder
@SequenceGenerator(
        name = "user_activity_sequence_generator",
        sequenceName = "user_activity_id_sequence",
        allocationSize = 1)
data class UserActivity(
        @Id
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "user_activity_sequence_generator")
        val id: Long?,
        @ManyToOne
        val rewardsUser: RewardsUser,
        val type: Activity,
        val description: String,
        val activityDate: LocalDateTime
)