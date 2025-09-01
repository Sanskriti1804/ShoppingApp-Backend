package com.example.model

import kotlinx.serialization.Serializable

//DTO - data transfer object - moves data between layers
@Serializable       //KTOR automatically converts JSON
data class LoginRequest(
    val email : String,
    val password : String
)