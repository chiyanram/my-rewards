package com.rmurugaian.myrewards.service

import com.rmurugaian.myrewards.domain.RewardsUserBuilder
import com.rmurugaian.myrewards.dto.CreateRewardsUserRequest
import com.rmurugaian.myrewards.dto.CreateRewardsUserResponse
import com.rmurugaian.myrewards.exception.NotFoundException
import com.rmurugaian.myrewards.facade.AuthenticationFacade
import com.rmurugaian.myrewards.mapper.RewardsUserMapper
import com.rmurugaian.myrewards.repository.RewardsUserRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

private val logger = KotlinLogging.logger {}

@Service
@Transactional(readOnly = true)
class DefaultRewardsUserService(
        val rewardsUserMapper: RewardsUserMapper,
        val authenticationFacade: AuthenticationFacade,
        val repository: RewardsUserRepository) : RewardsUserService {

    @Transactional
    override fun save(createRewardsUserRequest: CreateRewardsUserRequest): CreateRewardsUserResponse {

        val name = authenticationFacade.authentication().name
        logger.info { "currently logged in user name is : {} $name" }

        val rewardsUser =
                RewardsUserBuilder.builder()
                        .setFirstName(createRewardsUserRequest.firstName)
                        .setLastName(createRewardsUserRequest.lastName)
                        .setUserName(name)
                        .create()

        val savedUser = repository.save(rewardsUser)
        logger.info { "new rewards user saved with id {} ${savedUser.id}" }
        return rewardsUserMapper.entityToApi(savedUser)
    }

    override fun findByUser(userName: String): CreateRewardsUserResponse {
        return repository.findByUserName(userName)
                .map { rewardsUserMapper.entityToApi(it) }
                .orElseThrow { NotFoundException("rewards user not found for {} $userName") }
    }
}