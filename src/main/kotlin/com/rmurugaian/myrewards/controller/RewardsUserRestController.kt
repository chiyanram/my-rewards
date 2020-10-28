package com.rmurugaian.myrewards.controller

import com.rmurugaian.myrewards.dto.CreateRewardsUserRequest
import com.rmurugaian.myrewards.dto.RewardsUserDTO
import com.rmurugaian.myrewards.service.RewardsUserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class RewardsUserRestController(
        val rewardsUserService: RewardsUserService) {

    @PostMapping
    fun save(@RequestBody createRewardsUserRequest: CreateRewardsUserRequest): RewardsUserDTO {
        return rewardsUserService.save(createRewardsUserRequest)
    }

    @GetMapping
    fun get(): List<RewardsUserDTO> {
        return rewardsUserService.all()
    }
}