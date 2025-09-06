package com.example.config

import com.example.data.schema.Orders
import com.example.data.schema.Products
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseFactory {

    private val config = HikariConfig().apply {
        jdbcUrl = System.getenv("DB_URL") ?: "jdbc:postgresql://localhost:5432/shopping_db"
        driverClassName = "org.postgresql.Driver"
        username = System.getenv("POSTGRES_USER") ?: "admin"
        password = System.getenv("POSTGRES_PASSWORD") ?: "postgresSecret"
        maximumPoolSize = 7
        isReadOnly = false
        transactionIsolation = "TRANSACTION_SERIALIZABLE"
    }

    private val dataSource = HikariDataSource(config)

    val database = Database.connect(dataSource)

    fun init() {
        transaction(database) {
            addLogger(StdOutSqlLogger)
            println("Dropping tables if exist...")
            SchemaUtils.drop(Products, Orders)

            println("Creating tables Orders & Products...")
            SchemaUtils.create(Orders, Products)
            println("Tables created successfully")

            exec("SELECT table_name FROM information_schema.tables WHERE table_schema = 'public'") {
                println("Tables in public schema:")
                while (it.next()) {
                    println(it.getString("table_name"))
                }
            }
        }

    }
}

    //MANUAL DB SETUP W/O HIKARI
//    val database = Database.connect(
//        url = "jdbc:postgresql://localhost:5432/shopping_db",
//        user = "admin",
//        password = "postgresSecret",
//        driver = "org.postgresql.Driver"
//
//    )
