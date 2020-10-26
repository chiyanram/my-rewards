package com.rmurugaian.myrewards.mapper

import com.rmurugaian.myrewards.domain.Account
import com.rmurugaian.myrewards.dto.AccountDTO
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface AccountMapper {


    @Mappings(
            Mapping(source = "bank.name", target = "bank"),
            Mapping(source = "user.userName", target = "user")
    )
    fun entityToApi(entity: Account): AccountDTO
}