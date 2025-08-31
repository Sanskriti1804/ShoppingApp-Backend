package com.example.routes

import com.example.config.EnvConfig
import com.example.model.LoginRequest
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.HttpStatusCode

fun Route.shiprocketAuthRoutes(){
    post("/login"){
        val request = call.receive<LoginRequest>()

        if (request.email != EnvConfig.shiprocketEmail || request.password != EnvConfig.shiprocketPassword){
            call.respondText("Invalid Credentials", status = HttpStatusCode.Unauthorized)
            return@post
        }

    }
}