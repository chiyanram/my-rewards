package com.rmurugaian.myrewards.spi

import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class ChasePointsService : PointsService {
    override fun balance(accountIdentifier: String): Long {
        return Random.nextLong(10000, 30000)
    }

    override fun type(): String {
        return "Chase"
    }
}