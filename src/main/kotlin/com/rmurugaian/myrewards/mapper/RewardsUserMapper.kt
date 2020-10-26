package com.rmurugaian.myrewards.mapper

import com.rmurugaian.myrewards.domain.RewardsUser
import com.rmurugaian.myrewards.dto.RewardsUserDTO
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface RewardsUserMapper {

    fun entityToApi(entity: RewardsUser): RewardsUserDTO

    @Mapping(target = "id", ignore = true)
    fun apiToEntity(api: RewardsUserDTO): RewardsUser
}