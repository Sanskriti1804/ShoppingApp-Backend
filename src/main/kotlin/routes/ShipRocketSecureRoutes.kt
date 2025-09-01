package com.example.routes

import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.shiprocketSecureRoutes(){
    authenticate("auth-jwt"){       //JWT guard around routes. (likes bouncers outside club)
        get("/protected"){      //if the client calls /protected with valid JWT tokem then only - authoprized
            call.respondText("You are authorized")
        }
    }
}