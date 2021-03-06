package com.rmurugaian.myrewards.repository

import com.rmurugaian.myrewards.domain.RewardsUser
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RewardsUserRepository : JpaRepository<RewardsUser, Long> {
    fun findByUserName(userName: String): Optional<RewardsUser>
}