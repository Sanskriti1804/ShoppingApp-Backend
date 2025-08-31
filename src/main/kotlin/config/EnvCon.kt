package com.example.config

import io.github.cdimascio.dotenv.Dotenv

object EnvConfig {
    private val dotenv : Dotenv = Dotenv.load()// automatically reads .env

    val shiprocketEmail: String = dotenv["SHIPROCKET_API_EMAIL"]
        ?: throw Exception("SHIPROCKET_API_EMAIL not found in .env")

    val shiprocketPassword : String = dotenv["SHIPROCKET_API_PASSWORD"]
        ?: throw Exception("SHIPROCKET_API_EMAIL not found in .env")
}