package com.rmurugaian.myrewards.repository

import com.rmurugaian.myrewards.domain.UserActivity
import org.springframework.data.jpa.repository.JpaRepository

interface UserActivityRepository : JpaRepository<UserActivity, Long> {

    fun findAllByRewardsUser_UserNameOrderByActivityDate(userName: String): List<UserActivity>

}