package com.example.routes

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.config.EnvConfig
import com.example.model.LoginRequest
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.HttpStatusCode
import java.util.Date

fun Route.shiprocketAuthRoutes(){
    post("/login"){     //route //login - endpoint
        //call - current HTTP call
        //receive<LoginRequest>() - reads the incoming JSON body - deserializes into LoginRequest(in email and password)
        val request = call.receive<LoginRequest>()

        //validates the request against the secrets in .env
        if (request.email != EnvConfig.shiprocketEmail || request.password != EnvConfig.shiprocketPassword){
            call.respondText("Invalid Credentials", status = HttpStatusCode.Unauthorized)       //unatuthorized - 401
            return@post
        }

        //generate JWT token
        val token = JWT.create()
            .withAudience(EnvConfig.jwtAudience)
            .withIssuer(EnvConfig.jwtIssuer)
            .withClaim("email", request.email)
            .withExpiresAt(Date(System.currentTimeMillis() + 3600000))
            .sign(Algorithm.HMAC256(EnvConfig.jwtSecret))       //signs jwt claim(email) with choosedn algo(hHMHAC)

        call.respond(mapOf(
            "token" to token,
            "expiresIn" to 3600,
            "email" to request.email
        ))       //to <bearer<token>>
    }
}