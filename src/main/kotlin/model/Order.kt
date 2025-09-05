package com.example.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class OrderResponse(
    val data : OrderData
)

@Serializable
data class OrderData(
    val id : Long,
    @SerialName("customer_name") val customerName : String,
    @SerialName("customer_email") val customerEmail : String,
    @SerialName("customer_phone") val customerPhone : String,
    @SerialName("customer_address") val customerAddress : String,
    @SerialName("customer_city") val customerCity : String,
    @SerialName("customer_state") val customerState : String,
    @SerialName("customer_pincode") val customerPinCode : String,
    val total : Double,
    val status : String,
    @SerialName("payment_method") val paymentMethod : String,
    @SerialName("order_date") val orderDate : String,
    val products : List<Product>
)

@Serializable
data class Product(
    val id : Long,
    @SerialName("order_id") val orderId : Long,
    @SerialName("product_id") val productId : Long,
    val name : String,
    val sku : String,
    val size : String?,
    val price : Double,
    val quantity : Int,
    val status : String,
)
