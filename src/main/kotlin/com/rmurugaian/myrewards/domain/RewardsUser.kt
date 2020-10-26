package com.rmurugaian.myrewards.domain

import com.github.pozo.KotlinBuilder
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.math.BigDecimal
import javax.persistence.*

@Entity
@KotlinBuilder
data class RewardsUser(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        var id: Long,
        var firstName: String,
        @Column(nullable = false, unique = true)
        var userName: String,
        var lastName: String) {

    var totalPoints: Long = 0L
    var totalCash: BigDecimal = BigDecimal.ZERO

    @OneToMany(
            mappedBy = "user",
            cascade = [CascadeType.ALL],
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @Fetch(FetchMode.JOIN)
    var accounts: MutableList<Account> = ArrayList()

    fun addAccount(account: Account) {
        accounts.add(account)
        account.user = this
    }
}