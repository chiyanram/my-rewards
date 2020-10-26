package com.rmurugaian.myrewards.service

import com.rmurugaian.myrewards.domain.RewardsUserBuilder
import com.rmurugaian.myrewards.dto.RewardsUserDTO
import com.rmurugaian.myrewards.facade.AuthenticationFacade
import com.rmurugaian.myrewards.mapper.RewardsUserMapper
import com.rmurugaian.myrewards.repository.RewardsUserRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

private val logger = KotlinLogging.logger {}

@Service
@Transactional(readOnly = true)
class DefaultRewardsUserService(
        val rewardsUserMapper: RewardsUserMapper,
        val authenticationFacade: AuthenticationFacade,
        val repository: RewardsUserRepository) : RewardsUserService {

    @Transactional
    override fun save(rewardsUserDTO: RewardsUserDTO): RewardsUserDTO {

        val name = authenticationFacade.authentication().name
        logger.info { "currently logged in user name is : {} $name" }

        val rewardsUser =
                RewardsUserBuilder.builder()
                        .setFirstName(rewardsUserDTO.firstName)
                        .setLastName(rewardsUserDTO.lastName)
                        .setUserName(name)
                        .create()

        val savedUser = repository.save(rewardsUser)
        logger.info { "new rewards user saved with id {} ${savedUser.id}" }
        return rewardsUserMapper.entityToApi(savedUser)
    }

    override fun all(): List<RewardsUserDTO> {
        return repository.findAll()
                .stream()
                .map { rewardsUserMapper.entityToApi(it) }
                .collect(Collectors.toList())
    }
}