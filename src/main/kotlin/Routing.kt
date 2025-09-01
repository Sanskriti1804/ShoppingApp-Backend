package com.example

import com.example.routes.shiprocketAuthRoutes
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        shiprocketAuthRoutes()
//        get("/") {
//            call.respondText("Hello World!")
//        }
    }
}
