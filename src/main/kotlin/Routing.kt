package com.example

import com.example.clients.ShiprocketClient
import com.example.clients.repos.OrderRepository
import com.example.routes.orderRoutes
import com.example.routes.shiprocketAuthRoutes
import com.example.routes.shiprocketSecureRoutes
import io.ktor.client.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting(
    shiprocketClient: ShiprocketClient,
    orderRepository: OrderRepository
) {
    routing {
        shiprocketAuthRoutes()
        shiprocketSecureRoutes()
        orderRoutes(shiprocketClient, orderRepository)
        get("/") {
            call.respondText("Hello World!")
        }
    }
}
