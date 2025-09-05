package com.example.clients.repos

import com.example.config.DatabaseFactory
import com.example.data.schema.Orders
import com.example.data.schema.Products
import com.example.model.OrderData
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class OrderRepository (private val databaseFactory: DatabaseFactory){

    suspend fun saveOrder(order : OrderData){
        transaction(databaseFactory.database) {
            Orders.insert {
                it[id] = order.id
                it[customerName] = order.customerName
                it[customerEmail] = order.customerEmail
                it[customerPhone] = order.customerPhone
                it[customerAddress] = order.customerAddress
                it[customerCity] = order.customerCity
                it[customerState] = order.customerState
                it[customerPinCode] = order.customerPinCode
                it[total] = order.total
                it[status] = order.status
                it[paymentMethod] = order.paymentMethod
                it[orderDate] = order.orderDate
            }
            
            order.products.forEach{ product ->
                Products.insert {
                    it[id] = product.id
                    it[orderId] = product.orderId
                    it[productId] = product.productId
                    it[name] = product.name
                    it[sku] = product.sku
                    it[size] = product.size
                    it[price] = product.price
                    it[quantity] = product.quantity
                    it[status] = product.status
                }
            }
        }
    }
}