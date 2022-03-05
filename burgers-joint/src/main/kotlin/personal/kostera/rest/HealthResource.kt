package personal.kostera.rest

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import personal.kostera.component.HealthService

fun Route.health(healthService: HealthService) {
    route("/health") {
        get {
            call.respond(healthService.getHealthStatus())
        }
    }
}