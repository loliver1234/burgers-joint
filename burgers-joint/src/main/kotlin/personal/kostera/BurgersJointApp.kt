package personal.kostera

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.routing.*
import personal.kostera.component.BurgersService
import personal.kostera.component.HealthService
import personal.kostera.config.ApplicationConfig
import personal.kostera.factory.RestServerFactory
import personal.kostera.rest.health
import personal.kostera.rest.installCreateOrderRoute
import personal.kostera.utils.getLogger
import personal.kostera.utils.printConfig
import kotlin.system.exitProcess

private val logger = getLogger("personal.kostera.BurgersJointApp")

fun Application.mainModule(
    burgersService: BurgersService, healthService: HealthService
) {
    install(CallLogging)
    install(ContentNegotiation) {
        jackson {
            configure(SerializationFeature.INDENT_OUTPUT, true)
        }
    }
    install(Routing) {
        health(healthService)
        installCreateOrderRoute(burgersService)
    }
}

fun main() {
    logger.printConfig(
        ApplicationConfig
    )

    val currentMillis = System.currentTimeMillis()
    val burgersService = BurgersService()
    val healthService = HealthService()
    val server = RestServerFactory.getRestServer(burgersService, healthService)

    try {
        server.start()
        logger.info("Application started in ${System.currentTimeMillis() - currentMillis} ms")
    } catch (any: Exception) {
        logger.error("Exception while application initialization", any)
        exitProcess(-1)
    }
}