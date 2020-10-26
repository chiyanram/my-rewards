package com.rmurugaian.myrewards.mapper


import com.rmurugaian.myrewards.domain.Bank
import com.rmurugaian.myrewards.dto.BankDTO
import org.mapstruct.InjectionStrategy.CONSTRUCTOR
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring", injectionStrategy = CONSTRUCTOR)
interface BankMapper {

    fun entityToApi(entity: Bank): BankDTO

    @Mapping(target = "id", ignore = true)
    fun apiToEntity(api: BankDTO): Bank
}
