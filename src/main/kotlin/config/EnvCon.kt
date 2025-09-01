package com.example.config

import io.github.cdimascio.dotenv.Dotenv

object EnvConfig {
    private val dotenv : Dotenv = Dotenv.configure().ignoreIfMissing().load()

    val shiprocketEmail: String = dotenv["SHIPROCKET_API_EMAIL"]
        ?: throw Exception("SHIPROCKET_API_EMAIL not found in .env")

    val shiprocketPassword : String = dotenv["SHIPROCKET_API_PASSWORD"]
        ?: throw Exception("SHIPROCKET_API_PASSWORD not found in .env")

    val jwtSecret: String = dotenv["JWT_SECRET"]
        ?: throw Exception("JWT_SECRET not found in .env")

    val jwtIssuer: String = dotenv["JWT_ISSUER"]
        ?: "com.example.shopping"

    val jwtAudience: String = dotenv["JWT_AUDIENCE"]
        ?: "com.example.shopping.backend"

    val jwtRealm: String = dotenv["JWT_REALM"]
        ?: "Shopping Backend Authentication"

}