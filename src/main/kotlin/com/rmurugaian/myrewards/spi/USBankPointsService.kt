package com.rmurugaian.myrewards.spi

import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class USBankPointsService : PointsService {

    override fun balance(accountIdentifier: String): Long {
        return Random.nextLong(17000, 65000)
    }

    override fun type(): String {
        return "USBank"
    }
}