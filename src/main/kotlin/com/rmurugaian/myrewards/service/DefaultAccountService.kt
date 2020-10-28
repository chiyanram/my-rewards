package com.rmurugaian.myrewards.service

import com.rmurugaian.myrewards.domain.AccountBuilder
import com.rmurugaian.myrewards.dto.AccountDTO
import com.rmurugaian.myrewards.dto.CreateAccountRequest
import com.rmurugaian.myrewards.mapper.AccountMapper
import com.rmurugaian.myrewards.repository.AccountRepository
import com.rmurugaian.myrewards.repository.BankRepository
import com.rmurugaian.myrewards.repository.RewardsUserRepository
import com.rmurugaian.myrewards.spi.PointsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.math.MathContext
import java.util.function.Function
import java.util.stream.Collectors

@Service
@Transactional(readOnly = true)
class DefaultAccountService(
        pointsService: List<PointsService>,
        val accountMapper: AccountMapper,
        val rewardsUserRepository: RewardsUserRepository,
        val bankRepository: BankRepository,
        val accountRepository: AccountRepository) : AccountService {

    private val pointServiceByBank: Map<String, PointsService> = pointsService.stream()
            .collect(Collectors.toMap(PointsService::type, Function.identity()))


    @Transactional
    override fun save(createAccountRequest: CreateAccountRequest): AccountDTO {

        val user = rewardsUserRepository.findByUserName(createAccountRequest.user)
        val bank = bankRepository.findByName(createAccountRequest.bank)

        val points = (pointServiceByBank[createAccountRequest.bank]
                ?: error("bank not supported")).balance(createAccountRequest.accountIdentifier)

        val cash = BigDecimal.valueOf(points).multiply(bank.conversionRate, MathContext.DECIMAL64)

        val accountEntity = AccountBuilder.builder()
                .setAccountIdentifier(createAccountRequest.accountIdentifier)
                .setPoints(points)
                .setCash(cash)
                .create()
        accountEntity.bank = bank
        accountEntity.user = user
        val savedAccount = accountRepository.save(accountEntity)

        user.totalPoints = user.totalPoints + points
        user.totalCash = user.totalCash + cash
        user.addAccount(savedAccount)
        rewardsUserRepository.save(user)

        return accountMapper.entityToApi(savedAccount)
    }

    override fun accountsByName(userName: String): List<AccountDTO> {
        return accountRepository.findAllByUser_UserName(userName)
                .stream()
                .map { accountMapper.entityToApi(it) }
                .collect(Collectors.toList())
    }
}