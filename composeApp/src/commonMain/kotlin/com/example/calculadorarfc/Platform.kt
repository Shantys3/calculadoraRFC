package com.example.calculadorarfc

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform