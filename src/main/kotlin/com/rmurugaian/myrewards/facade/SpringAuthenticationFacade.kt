package com.rmurugaian.myrewards.facade

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class SpringAuthenticationFacade : AuthenticationFacade {

    override fun authentication(): Authentication {
        return SecurityContextHolder.getContext().authentication
    }
}