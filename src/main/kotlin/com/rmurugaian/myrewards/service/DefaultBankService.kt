package com.rmurugaian.myrewards.service

import com.rmurugaian.myrewards.dto.BankDTO
import com.rmurugaian.myrewards.mapper.BankMapper
import com.rmurugaian.myrewards.repository.BankRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.util.stream.Collectors

private val logger = KotlinLogging.logger {}

@Service
@Transactional(readOnly = true)
class DefaultBankService(
        val bankMapper: BankMapper,
        val bankRepository: BankRepository) : BankService {

    @Transactional
    override fun save(bankDTO: BankDTO): BankDTO {
        logger.info { "new bank added into system with name ${bankDTO.name}" }

        val apiToEntity = bankMapper.apiToEntity(bankDTO)
        val entity = bankRepository.save(apiToEntity)
        return bankMapper.entityToApi(entity)
    }

    override fun all(): List<BankDTO> {
        return bankRepository.findAll()
                .stream()
                .map { bankMapper.entityToApi(it) }
                .collect(Collectors.toList())
    }
}