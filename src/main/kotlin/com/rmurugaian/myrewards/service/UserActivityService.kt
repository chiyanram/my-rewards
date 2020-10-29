package com.rmurugaian.myrewards.service

import com.rmurugaian.myrewards.dto.UserActivityResponse


interface UserActivityService {

    fun activitiesByUser(userName: String): Iterable<UserActivityResponse>

}