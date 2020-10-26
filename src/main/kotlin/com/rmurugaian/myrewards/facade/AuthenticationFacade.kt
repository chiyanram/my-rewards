package com.rmurugaian.myrewards.facade

import org.springframework.security.core.Authentication

interface AuthenticationFacade {

    fun authentication(): Authentication
}