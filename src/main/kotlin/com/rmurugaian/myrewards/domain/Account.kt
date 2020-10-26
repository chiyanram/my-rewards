package com.rmurugaian.myrewards.domain

import com.github.pozo.KotlinBuilder
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.math.BigDecimal
import javax.persistence.*

@Entity
@KotlinBuilder
data class Account(
        @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
        val id: Long,
        val accountIdentifier: String,
        var points: Long,
        var cash: BigDecimal) {

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    var bank: Bank? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    var user: RewardsUser? = null
}