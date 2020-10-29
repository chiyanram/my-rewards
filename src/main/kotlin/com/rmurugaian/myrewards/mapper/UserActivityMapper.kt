package com.rmurugaian.myrewards.mapper

import com.rmurugaian.myrewards.domain.UserActivity
import com.rmurugaian.myrewards.dto.UserActivityResponse
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface UserActivityMapper {

    @Mappings(
            Mapping(source = "rewardsUser.userName", target = "userName")
    )
    fun entityToApi(userActivity: UserActivity): UserActivityResponse
}

