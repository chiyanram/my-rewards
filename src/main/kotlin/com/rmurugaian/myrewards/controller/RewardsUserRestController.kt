package com.rmurugaian.myrewards.controller

import com.rmurugaian.myrewards.dto.RewardsUserDTO
import com.rmurugaian.myrewards.service.RewardsUserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class RewardsUserRestController(
        val rewardsUserService: RewardsUserService) {

    @PostMapping
    fun save(@RequestBody rewardsUserDTO: RewardsUserDTO): RewardsUserDTO {
        return rewardsUserService.save(rewardsUserDTO)
    }

    @GetMapping
    fun get(): List<RewardsUserDTO> {
        return rewardsUserService.all()
    }
}