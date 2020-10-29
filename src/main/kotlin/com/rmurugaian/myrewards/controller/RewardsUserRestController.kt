package com.rmurugaian.myrewards.controller

import com.rmurugaian.myrewards.dto.CreateRewardsUserRequest
import com.rmurugaian.myrewards.dto.CreateRewardsUserResponse
import com.rmurugaian.myrewards.service.RewardsUserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class RewardsUserRestController(
        val rewardsUserService: RewardsUserService) {

    @PostMapping
    fun save(@RequestBody createRewardsUserRequest: CreateRewardsUserRequest): CreateRewardsUserResponse {
        return rewardsUserService.save(createRewardsUserRequest)
    }

    @GetMapping("/{userName}")
    fun get(@PathVariable userName: String): CreateRewardsUserResponse {
        return rewardsUserService.findByUser(userName)
    }
}