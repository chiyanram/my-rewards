package com.rmurugaian.myrewards.domain

import com.github.pozo.KotlinBuilder
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.math.BigDecimal
import javax.persistence.*

@Entity
@KotlinBuilder
@SequenceGenerator(
        name = "account_sequence_generator",
        sequenceName = "account_id_sequence",
        allocationSize = 1)
data class Account(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence_generator")
        val id: Long,
        @Column(unique = true)
        val accountIdentifier: String,
        var points: Long,
        var cash: BigDecimal,
        @ManyToOne(fetch = FetchType.LAZY)
        @Fetch(FetchMode.JOIN)
        val bank: Bank,
        @ManyToOne(fetch = FetchType.LAZY)
        @Fetch(FetchMode.JOIN)
        val user: RewardsUser)