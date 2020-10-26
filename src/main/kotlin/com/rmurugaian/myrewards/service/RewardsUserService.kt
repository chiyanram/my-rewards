package com.rmurugaian.myrewards.service

import com.rmurugaian.myrewards.dto.RewardsUserDTO

interface RewardsUserService {

    fun save(rewardsUserDTO: RewardsUserDTO): RewardsUserDTO

    fun all(): List<RewardsUserDTO>
}