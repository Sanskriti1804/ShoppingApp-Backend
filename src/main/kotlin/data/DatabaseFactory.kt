package com.example.data

import org.jetbrains.exposed.sql.Database

class DatabaseFactory {

    val database = Database.connect(
        url = "jdbc:postgresql://localhost:5432/shopping_db",
        user = "admin",
        password = "postgresSecret",
        driver = "org.postgresql.Driver"

    )
}