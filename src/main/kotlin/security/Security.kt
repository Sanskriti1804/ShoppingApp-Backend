package com.example.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.config.EnvConfig
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import java.util.*

fun Application.configureShiprocketSecurity(){
    install(Authentication){
        jwt("auth-jwt") {
            realm = EnvConfig.jwtRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(EnvConfig.jwtSecret))
                    .withAudience(EnvConfig.jwtAudience)
                    .withIssuer(EnvConfig.jwtIssuer)
                    .build()
            )

            validate { jwtCredential ->
                val email = jwtCredential.payload.getClaim("email").asString()
                if (email.isNotEmpty()) JWTPrincipal(jwtCredential.payload) else null
            }
        }
    }
}