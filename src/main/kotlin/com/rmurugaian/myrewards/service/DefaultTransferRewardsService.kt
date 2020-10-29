package com.rmurugaian.myrewards.service

import com.rmurugaian.myrewards.BalanceException
import com.rmurugaian.myrewards.domain.Activity
import com.rmurugaian.myrewards.domain.RewardsUser
import com.rmurugaian.myrewards.domain.UserActivity
import com.rmurugaian.myrewards.dto.CreateAccountResponse
import com.rmurugaian.myrewards.dto.TransferRewardsRequest
import com.rmurugaian.myrewards.exception.NotFoundException
import com.rmurugaian.myrewards.repository.RewardsUserRepository
import com.rmurugaian.myrewards.repository.UserActivityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.stream.Collectors

@Service
@Transactional
class DefaultTransferRewardsService(
        val accountService: AccountService,
        val userActivityRepository: UserActivityRepository,
        val rewardsUserRepository: RewardsUserRepository) : TransferRewardsService {

    override fun transfer(transferRewardsRequest: TransferRewardsRequest): List<CreateAccountResponse> {
        val userName = transferRewardsRequest.userName
        val transferPoints = transferRewardsRequest.totalPoints

        val rewardsUser =
                rewardsUserRepository.findByUserName(userName)
                        .orElseThrow { NotFoundException("rewards user not found for the $userName") }

        val accounts = rewardsUser.accounts.stream()
                .filter { it.points > 0 }
                .collect(Collectors.toList())

        val existingUserPoints = rewardsUser.totalPoints

        if (transferPoints > existingUserPoints) {
            throw BalanceException("Not Enough balance")
        }

        var remaining = transferPoints
        for (account in accounts) {
            remaining = account.points - remaining
            val conversionRate = account.bank.conversionRate
            val cash = conversionRate.multiply(BigDecimal(remaining))
            if (remaining >= 0) {
                account.points = remaining
                account.cash = cash
                break
            }
            account.points = 0L
            account.cash = BigDecimal.ZERO
        }

        updateRewardsUser(rewardsUser)

        publishUserActivity(rewardsUser, transferRewardsRequest)

        return accountService.accountsByName(userName)
    }

    private fun updateRewardsUser(rewardsUser: RewardsUser) {
        rewardsUser.totalPoints =
                rewardsUser.accounts
                        .stream()
                        .map { it.points }
                        .reduce { sum, element -> sum + element }
                        .orElse(0L)

        rewardsUser.totalCash =
                rewardsUser.accounts
                        .stream()
                        .map { it.cash }
                        .reduce { sum, element -> element.add(sum) }
                        .orElse(BigDecimal.ZERO)
    }

    private fun publishUserActivity(rewardsUser: RewardsUser, transferRewardsRequest: TransferRewardsRequest) {
        val userActivity =
                UserActivity(
                        null,
                        rewardsUser = rewardsUser,
                        type = Activity.TRANSFER_REWARDS,
                        description = transferRewardsRequest.description,
                        activityDate = LocalDateTime.now())

        userActivityRepository.save(userActivity)
    }
}