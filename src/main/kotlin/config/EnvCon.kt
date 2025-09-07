package com.example.config

import io.github.cdimascio.dotenv.Dotenv

object EnvConfig {

    private val dotenv : Dotenv = Dotenv.configure().ignoreIfMissing().load()

    //shiprocket
    val shiprocketEmail: String = System.getenv("SHIPROCKET_API_EMAIL")
        ?: dotenv["SHIPROCKET_API_EMAIL"]
        ?: throw Exception("SHIPROCKET_API_EMAIL n1ot found in env or .env")

    val shiprocketPassword : String = System.getenv("SHIPROCKET_API_PASSWORD")
        ?: dotenv["SHIPROCKET_API_PASSWORD"]
        ?: throw Exception("SHIPROCKET_API_PASSWORD not found in env or .env")

    val jwtSecret: String = System.getenv("JWT_SECRET")
        ?: dotenv["JWT_SECRET"]
        ?: throw Exception("JWT_SECRET not found in env or .env")

    val jwtIssuer: String = System.getenv("JWT_ISSUER")
        ?: dotenv["JWT_ISSUER"]
        ?: "com.example.shopping"

    val jwtAudience: String = System.getenv("JWT_AUDIENCE")
        ?: dotenv["JWT_AUDIENCE"]
        ?: "com.example.shopping.backend"

    val jwtRealm: String = System.getenv("JWT_REALM")
        ?: dotenv["JWT_REALM"]
        ?: "Shopping Backend Authentication"


    //sendgrid
    val sendgridApiKey : String = System.getenv("SENDGRID_API_KEY")
        ?: dotenv["SENDGRID_API_KEY"]
        ?: throw  Exception("SENDGRID_APIKEY not found in env or .env")
}