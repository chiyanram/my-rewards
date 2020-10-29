package com.rmurugaian.myrewards.mapper

import com.rmurugaian.myrewards.domain.RewardsUser
import com.rmurugaian.myrewards.dto.CreateRewardsUserResponse
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = [AccountMapper::class])
interface RewardsUserMapper {

    fun entityToApi(entity: RewardsUser): CreateRewardsUserResponse

    @Mapping(target = "id", ignore = true)
    fun apiToEntity(api: CreateRewardsUserResponse): RewardsUser
}