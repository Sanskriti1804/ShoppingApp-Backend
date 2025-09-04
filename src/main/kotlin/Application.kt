package com.example

import com.example.data.DatabaseFactory
import com.example.security.configureShiprocketSecurity
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*

fun main(args: Array<String>) {     //entry point of app
    io.ktor.server.netty.EngineMain.main(args)      //Netty is a high-performance asynchronous networking framework
}

fun Application.module() { // main application module - setup code for server what

    val databaseFactory = DatabaseFactory()
    databaseFactory.database

    configureSerialization()
    configureShiprocketSecurity()
    configureRouting()
}

