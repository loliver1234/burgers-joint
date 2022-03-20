import io.ktor.server.engine.*
import io.restassured.RestAssured
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import personal.kostera.component.BurgersService
import personal.kostera.component.HealthService
import personal.kostera.config.ApplicationConfig
import personal.kostera.factory.RestServerFactory


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class BurgersAppTest(
    private val currentTimeSupplier: () -> Long = { 16803868000L },
    private val testStartTime: Long = 16403868000L
) {

    private lateinit var server: ApplicationEngine

    @BeforeAll
    fun beforeClass() {
        server = getRestServer().start()
        RestAssured.port = ApplicationConfig.serverPort
    }

    @AfterAll
    fun afterClass() {
        server.stop(0, 0)
    }

    private fun getRestServer(): ApplicationEngine =
        RestServerFactory.getRestServer(
            BurgersService(currentTimeSupplier),
            HealthService(testStartTime, currentTimeSupplier)
        )
}