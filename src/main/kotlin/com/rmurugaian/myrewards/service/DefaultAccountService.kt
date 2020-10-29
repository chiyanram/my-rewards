package com.rmurugaian.myrewards.service

import com.rmurugaian.myrewards.domain.AccountBuilder
import com.rmurugaian.myrewards.dto.CreateAccountRequest
import com.rmurugaian.myrewards.dto.CreateAccountResponse
import com.rmurugaian.myrewards.exception.NotFoundException
import com.rmurugaian.myrewards.mapper.AccountMapper
import com.rmurugaian.myrewards.repository.AccountRepository
import com.rmurugaian.myrewards.repository.BankRepository
import com.rmurugaian.myrewards.repository.RewardsUserRepository
import com.rmurugaian.myrewards.spi.PointsService
import org.slf4j.LoggerFactory
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

    private val logger = LoggerFactory.getLogger(DefaultAccountService::class.java)

    private val pointServiceByBank: Map<String, PointsService> = pointsService.stream()
            .collect(Collectors.toMap(PointsService::type, Function.identity()))


    @Transactional
    override fun save(createAccountRequest: CreateAccountRequest): CreateAccountResponse {

        val user = rewardsUserRepository.findByUserName(createAccountRequest.user)
                .orElseThrow { NotFoundException("rewards user not found for the ${createAccountRequest.user}") }

        val bank = bankRepository.findByName(createAccountRequest.bank)
                .orElseThrow { NotFoundException("bank not available for the name {} ${createAccountRequest.bank}") }

        val points = (pointServiceByBank[createAccountRequest.bank]
                ?: error("bank not supported")).balance(createAccountRequest.accountIdentifier)

        val cash = BigDecimal.valueOf(points).multiply(bank.conversionRate, MathContext.DECIMAL64)

        logger.debug("points from bank {} $points")
        logger.debug("cash for the points {} $points is $cash")

        val accountEntity = AccountBuilder.builder()
                .setAccountIdentifier(createAccountRequest.accountIdentifier)
                .setPoints(points)
                .setCash(cash)
                .setBank(bank)
                .setUser(user)
                .create()

        val savedAccount = accountRepository.save(accountEntity)

        user.totalPoints = user.totalPoints + points
        user.totalCash = user.totalCash + cash
        user.addAccount(savedAccount)

        return accountMapper.entityToApi(savedAccount)
    }

    override fun accountsByName(userName: String): List<CreateAccountResponse> {
        return accountRepository.findAllByUser_UserName(userName)
                .stream()
                .map { accountMapper.entityToApi(it) }
                .collect(Collectors.toList())
    }
}