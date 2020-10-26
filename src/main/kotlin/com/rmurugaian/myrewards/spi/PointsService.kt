package com.rmurugaian.myrewards.spi

interface PointsService {

    fun balance(accountIdentifier: String): Long

    fun type(): String

}