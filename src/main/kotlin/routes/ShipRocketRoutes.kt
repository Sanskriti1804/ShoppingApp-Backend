package com.example.routes

import com.example.config.EnvConfig
import com.example.model.LoginRequest
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.HttpStatusCode

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

    }
}