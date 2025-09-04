package com.example.data

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

class DatabaseFactory {

    private val config = HikariConfig().apply {
        jdbcUrl = "jdbc:postgresql://localhost:5432/shopping_db"
        driverClassName = "org.postgresql.Driver"
        username = "admin"
        password = "postgresSecret"
        isReadOnly = false
        maximumPoolSize = 7
        transactionIsolation = "TRANSACTION_SERIALIZABLE"
    }

    private val dataSource = HikariDataSource(config)

    val database = Database.connect(dataSource)

    //MANUAL DB SETUP W/O HIKARI
//    val database = Database.connect(
//        url = "jdbc:postgresql://localhost:5432/shopping_db",
//        user = "admin",
//        password = "postgresSecret",
//        driver = "org.postgresql.Driver"
//
//    )
}