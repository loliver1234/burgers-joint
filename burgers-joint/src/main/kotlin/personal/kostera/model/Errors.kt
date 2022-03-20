package personal.kostera.model

import com.fasterxml.jackson.annotation.JsonInclude
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import personal.kostera.utils.getCurrentTimeString

data class ErrorResponse(
    val message: String = "",
    @JsonInclude(JsonInclude.Include.NON_NULL) val code: Int? = null
)

sealed class Error(
    open val reason: Throwable? = null,
    open val error: ErrorResponse = ErrorResponse("Internal server error.", 0)
) {
    data class OrdersLocked(
        val currentTime: Long = System.currentTimeMillis(),
        override val error: ErrorResponse = ErrorResponse(
            "The orders are locked. Open hours are between 7 AM and 11 AM. Current time: ${
                getCurrentTimeString(
                    currentTime
                )
            }"
        )
    ) : Error()
}

suspend fun handleResponseOnError(error: Error, call: ApplicationCall): HttpStatusCode =
    when (error) {
        is Error.OrdersLocked -> {
            call.respond(HttpStatusCode.Locked, error.error)
            HttpStatusCode.Locked
        }
    }