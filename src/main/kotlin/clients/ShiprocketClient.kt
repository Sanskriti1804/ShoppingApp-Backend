package com.example.clients

import ch.qos.logback.core.subst.Token
import com.example.model.OrderResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*

class ShiprocketClient (private val httpClient: HttpClient, private val token: String){

    suspend fun getOrderById(orderId : Long): OrderResponse{
        return httpClient.get("https://apiv2.shiprocket.in/v1/external/orders/show"){
            headers{
//                append(HttpHeaders.ContentType, contentType(Application.Json.toString()))
                append(HttpHeaders.Authorization, "Bearer $token")
            }
        }.body()
    }
}