package com.rmurugaian.myrewards.service

import com.rmurugaian.myrewards.dto.UserActivityDTO


interface UserActivityService {

    fun activitiesByUser(userName: String): Iterable<UserActivityDTO>

}