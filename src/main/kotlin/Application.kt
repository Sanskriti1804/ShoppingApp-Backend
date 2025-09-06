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
import io.ktor.http.*
import io.ktor.server.plugins.cors.routing.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction


fun main(args: Array<String>) {     //entry point of app
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"))
    io.ktor.server.netty.EngineMain.main(args)      //Netty is a high-performance asynchronous networking framework
}

fun Application.module() { // main application module - setup code for server what

    //allows Android app or frontend call your Ktor backend without getting blocked due to cross-origin restrictions
    install(CORS) {
        anyHost()
        allowHeader(HttpHeaders.ContentType)
        allowHeader(HttpHeaders.Authorization)
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
    }

    println("JVM TZ = ${java.util.TimeZone.getDefault().id}")

    val databaseFactory = DatabaseFactory()
    databaseFactory.init()
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

