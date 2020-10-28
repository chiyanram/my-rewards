package com.rmurugaian.myrewards.service

import com.rmurugaian.myrewards.dto.CreateRewardsUserRequest
import com.rmurugaian.myrewards.dto.RewardsUserDTO

interface RewardsUserService {

    fun save(createRewardsUserRequest: CreateRewardsUserRequest): RewardsUserDTO

    fun all(): List<RewardsUserDTO>

}