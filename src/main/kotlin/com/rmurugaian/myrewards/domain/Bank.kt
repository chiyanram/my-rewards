package com.rmurugaian.myrewards.domain

import com.github.pozo.KotlinBuilder
import java.math.BigDecimal
import javax.persistence.*

@Entity
@KotlinBuilder
@SequenceGenerator(
        name = "bank_sequence_generator",
        sequenceName = "bank_id_sequence",
        allocationSize = 1)
data class Bank(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bank_sequence_generator")
        val id: Long,
        @Column(nullable = false, unique = true)
        val name: String,
        @Column(nullable = false)
        val conversionRate: BigDecimal)