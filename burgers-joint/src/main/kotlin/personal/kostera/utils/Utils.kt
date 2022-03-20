package personal.kostera.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import personal.kostera.config.ApplicationConfig
import java.text.SimpleDateFormat
import java.util.*

fun <T : Any> T.getLogger(): Logger = LoggerFactory.getLogger(this.javaClass.name)
fun getLogger(name: String): Logger = LoggerFactory.getLogger(name)

interface IBaseConfig

fun Logger.printConfig(vararg configs: IBaseConfig) {
    if (ApplicationConfig.printConfig) {
        configs.forEach { this.info(it.toString()) }
    }
}

private val currentTimeFormatter = SimpleDateFormat("HH:mm:ss")

fun getCurrentTimeString(timestamp: Long) = currentTimeFormatter.format(Date(timestamp))