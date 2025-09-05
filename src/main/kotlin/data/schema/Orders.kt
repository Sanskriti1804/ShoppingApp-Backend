package com.example.data.schema

import org.jetbrains.exposed.sql.Table

object Orders : Table("orders"){
    val id = long("id")
    val customerName = varchar("customer_name", 100)
    val customerEmail = varchar("customer_email", 100)
    val customerPhone = varchar("customer_phone", 20)
    val customerAddress = varchar("customer_address", 255)
    val customerCity = varchar("customer_city", 100)
    val customerState = varchar("customer_state", 100)
    val customerPinCode = varchar("customer_pincode", 20)
    val total = double("total")
    val status = varchar("status", 50)
    val paymentMethod = varchar("payment_method", 50)
    val orderDate = varchar("order_date", 50)

    override val primaryKey = PrimaryKey(id)

}

object Products : Table("products") {
    val id = long("id")
    val orderId = long("order_id").references(Orders.id)        //foreign key constraint
    val productId = long("product_id")
    val name = varchar("name", 150)
    val sku = varchar("sku", 100)
    val size = varchar("size", 50).nullable()
    val price = double("price")
    val quantity = integer("quantity")
    val status = varchar("status", 50)

    override val primaryKey = PrimaryKey(id)
}