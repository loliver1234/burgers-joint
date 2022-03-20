package endpoints

import BurgersAppTest
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.Matchers
import personal.kostera.utils.getCurrentTimeString
import kotlin.test.Test

private const val currentTestTimeLocked = 16803868000L
private const val currentTestTimeOpen = 1647763786549L

class CreateOrderTest : BurgersAppTest({ currentTestTimeOpen }) {
    @Test
    fun createOrder_shouldReturn_500_INTERNAL_SERVER_ERROR() {
        Given {
            body("[]")
            contentType(ContentType.JSON)
        } When {
            post("/order")
        } Then {
            statusCode(500)
        }
    }

    @Test
    fun createOrder_withEmptyBody_shouldReturn_400_BAD_REQUEST() {
        Given {
            body("")
            contentType(ContentType.JSON)
        } When {
            post("/order")
        } Then {
            statusCode(400)
        }
    }
}

class CreateOrderTestLocked : BurgersAppTest({ currentTestTimeLocked }) {

    @Test
    fun createOrder_shouldReturn_423_LOCKED() {
        Given {
            body("[]")
            contentType(ContentType.JSON)
        } When {
            post("/order")
        } Then {
            statusCode(423)
            body(
                "message", Matchers.equalTo(
                    "The orders are locked. Open hours are between 7 AM and 11 AM. Current time: ${
                        getCurrentTimeString(
                            currentTestTimeLocked
                        )
                    }"
                )
            )
        }
    }
}