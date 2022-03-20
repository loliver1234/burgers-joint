package endpoints

import BurgersAppTest
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.Matchers
import kotlin.test.Test

class GetHealthTest : BurgersAppTest() {

    @Test
    fun getHealth_shouldReturnGreen_200_OK() {
        When {
            get("/health")
        } Then {
            statusCode(200)
            body("status", Matchers.equalTo("GREEN"))
            body("uptime", Matchers.equalTo("111 hours, 6 minutes and 40 seconds"))
            body("gitRepositoryState.commitId", Matchers.not(Matchers.emptyOrNullString()))
            body("gitRepositoryState.commitIdAbbrev", Matchers.not(Matchers.emptyOrNullString()))
        }
    }
}