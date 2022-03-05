package personal.kostera.factory

import io.ktor.server.engine.applicationEngineEnvironment
import io.ktor.server.engine.connector
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import personal.kostera.component.BurgersService
import personal.kostera.component.HealthService
import personal.kostera.config.ApplicationConfig
import personal.kostera.mainModule

object RestServerFactory {
    fun getRestServer(
        burgersService: BurgersService, healthService: HealthService
    ) = embeddedServer(Netty, environment = applicationEngineEnvironment {
        connector {
            host = ApplicationConfig.serverHost
            port = ApplicationConfig.serverPort
        }
        module {
            mainModule(burgersService, healthService)
        }
    })
}