package de.mw

import de.mw.plugins.configureRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

val envHost = System.getenv("APP_HOST") ?: "0.0.0.0"

fun main() {
    embeddedServer(Netty, port = 8080, host = envHost, module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
}
