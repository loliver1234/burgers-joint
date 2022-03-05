package personal.kostera.config

import com.natpryce.konfig.*
import personal.kostera.utils.IBaseConfig

object ApplicationConfig : IBaseConfig {
    private const val propertiesResourceName = "application.properties"
    private val applicationConfig
        get() = EnvironmentVariables() overriding
                ConfigurationProperties.systemProperties() overriding
                ConfigurationProperties.fromResource(propertiesResourceName)

    private val serviceNameKey = Key("service.name", stringType)
    val serviceName = applicationConfig.getOrElse(
        serviceNameKey, "service-name"
    )

    private val serviceIdKey = Key("service.id", stringType)
    val serviceId = applicationConfig.getOrElse(
        serviceIdKey, "color"
    )

    private val printConfigKey = Key("print.config", booleanType)
    val printConfig = applicationConfig.getOrElse(
        printConfigKey,
        false
    )

    private val serverHostKey = Key("server.host", stringType)
    val serverHost = applicationConfig.getOrElse(serverHostKey, "0.0.0.0")

    private val serverPortKey = Key("server.port", intType)
    val serverPort = applicationConfig.getOrElse(serverPortKey, 8080)

    override fun toString() = """
        ${this.javaClass.simpleName}:
        ${serviceNameKey.name}: $serviceName
        ${serviceIdKey.name}: $serviceId
        ${printConfigKey.name}: $printConfig
        ${serverHostKey.name}: $serverHost
        ${serverPortKey.name}: $serverPort
    """.trimIndent()
}