package com.rmurugaian.myrewards.domain

import com.github.pozo.KotlinBuilder
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.math.BigDecimal
import javax.persistence.*

@Entity
@KotlinBuilder
@SequenceGenerator(
        name = "rewards_user_sequence_generator",
        sequenceName = "rewards_user_id_sequence",
        allocationSize = 1)
data class RewardsUser(
        @Id
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "rewards_user_sequence_generator")
        var id: Long,
        val firstName: String,
        @Column(nullable = false, unique = true)
        val userName: String,
        val lastName: String) {

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
    }
}