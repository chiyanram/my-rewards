package com.rmurugaian.myrewards.spi

import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class WellsFargoPointsService : PointsService {
    override fun balance(accountIdentifier: String): Long {
        return Random.nextLong(30000, 50000)
    }

    override fun type(): String {
        return "WellsFargo"
    }
}