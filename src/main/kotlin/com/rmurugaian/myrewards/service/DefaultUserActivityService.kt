package com.rmurugaian.myrewards.service

import com.rmurugaian.myrewards.dto.UserActivityDTO
import com.rmurugaian.myrewards.mapper.UserActivityMapper
import com.rmurugaian.myrewards.repository.UserActivityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
@Transactional(readOnly = true)
class DefaultUserActivityService(
        val userActivityMapper: UserActivityMapper,
        val userActivityRepository: UserActivityRepository) : UserActivityService {

    override fun activitiesByUser(userName: String): Iterable<UserActivityDTO> {
        return userActivityRepository.findAllByRewardsUser_UserName(userName)
                .stream()
                .map { userActivityMapper.entityToApi(it) }
                .collect(Collectors.toList())
    }
}