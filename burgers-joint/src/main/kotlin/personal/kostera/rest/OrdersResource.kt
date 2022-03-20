package personal.kostera.rest

import arrow.core.Either
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import personal.kostera.component.BurgersService
import personal.kostera.model.ErrorResponse
import personal.kostera.model.handleResponseOnError

fun Route.installCreateOrderRoute(
    burgersService: BurgersService
) {
    post("/order") {
        val requestList = runCatching {
            this.call.receive<List<Map<String, Any?>>>()
        }.getOrElse {
            this.call.respond(
                HttpStatusCode.BadRequest,
                ErrorResponse("Passed body is not in a proper json list format.")
            )
            return@post
        }
        when (val orderResult = burgersService.createOrder(requestList)) {
            is Either.Right -> {
                call.respond(HttpStatusCode.Created, orderResult.value)
            }
            is Either.Left -> {
                handleResponseOnError(orderResult.value, call)
            }
        }
    }
}