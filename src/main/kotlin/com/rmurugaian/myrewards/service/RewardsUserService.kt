package com.rmurugaian.myrewards.service

import com.rmurugaian.myrewards.dto.CreateRewardsUserRequest
import com.rmurugaian.myrewards.dto.CreateRewardsUserResponse

interface RewardsUserService {

    fun save(createRewardsUserRequest: CreateRewardsUserRequest): CreateRewardsUserResponse

    fun findByUser(userName: String): CreateRewardsUserResponse

}