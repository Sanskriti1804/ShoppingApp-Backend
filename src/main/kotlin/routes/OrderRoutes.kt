package com.example.routes

import com.example.clients.ShiprocketClient
import com.example.clients.repos.OrderRepository
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.orderRoutes(shiprocketClient: ShiprocketClient, orderRepository: OrderRepository){
    route("/orders"){
        get("/{id}") {
            val id = call.parameters["id"]?.toLongOrNull()
            if (id == null) {
                call.respondText("Invalid Order ID", status = HttpStatusCode.BadRequest)
                return@get
            }

            val orderResponse = shiprocketClient.getOrderById(id)
            orderRepository.saveOrder(orderResponse.data)
            call.respond(orderResponse)
        }
    }
}