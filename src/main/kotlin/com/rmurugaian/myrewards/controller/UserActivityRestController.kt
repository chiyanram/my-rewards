package com.rmurugaian.myrewards.controller

import com.rmurugaian.myrewards.dto.UserActivityResponse
import com.rmurugaian.myrewards.service.UserActivityService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserActivityRestController(val userActivityService: UserActivityService) {

    @GetMapping("/activities/{userName}")
    fun activities(@PathVariable userName: String): Iterable<UserActivityResponse> {
        return userActivityService.activitiesByUser(userName)
    }
}