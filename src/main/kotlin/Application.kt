package com.example

import com.example.clients.ShiprocketClient
import com.example.clients.repos.OrderRepository
import com.example.config.DatabaseFactory
import com.example.config.EnvConfig
import com.example.security.configureShiprocketSecurity
import io.ktor.client.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.client.plugins.contentnegotiation.*
import java.util.*
import io.ktor.client.engine.cio.*


fun main(args: Array<String>) {     //entry point of app
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"))
    io.ktor.server.netty.EngineMain.main(args)      //Netty is a high-performance asynchronous networking framework
}

fun Application.module() { // main application module - setup code for server what

    println("JVM TZ = ${java.util.TimeZone.getDefault().id}")

    val databaseFactory = DatabaseFactory()
    val orderRepository = OrderRepository(databaseFactory)
//    databaseFactory.database

    val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    val token = EnvConfig.jwtSecret

    val shiprocketClient = ShiprocketClient(httpClient, token)

    configureSerialization()
    configureShiprocketSecurity()
    configureRouting(shiprocketClient, orderRepository)
}

