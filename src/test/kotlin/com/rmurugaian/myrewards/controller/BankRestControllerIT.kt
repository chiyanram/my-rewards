package com.rmurugaian.myrewards.controller

import com.rmurugaian.myrewards.AbstractIntegrationSpec
import com.rmurugaian.myrewards.domain.Bank
import com.rmurugaian.myrewards.repository.BankRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import java.math.BigDecimal

class BankRestControllerIT : AbstractIntegrationSpec() {

    @Autowired
    private lateinit var bookRepository: BankRepository

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun shouldReturnAllBooks() {

        this.bookRepository.save(Bank(1L, "Chase", BigDecimal(2.5)))
        this.bookRepository.save(Bank(2L, "WellsFargo", BigDecimal(3.5)))

        this.webTestClient.get()
                .uri("/books")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful
                .expectBody().jsonPath("$.length()").isEqualTo(2)
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].isbn").isEqualTo(42)
                .jsonPath("$[0].title").isEqualTo("Java 14")
    }
}