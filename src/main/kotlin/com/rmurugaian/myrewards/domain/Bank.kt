package com.rmurugaian.myrewards.domain

import com.github.pozo.KotlinBuilder
import java.math.BigDecimal
import javax.persistence.*

@Entity
@KotlinBuilder
data class Bank(
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
        val id: Long,
        @Column(nullable = false, unique = true)
        val name: String,
        @Column(nullable = false)
        val conversionRate: BigDecimal)