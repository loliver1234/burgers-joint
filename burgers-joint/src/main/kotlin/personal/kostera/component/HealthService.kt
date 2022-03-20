package personal.kostera.component

import com.natpryce.konfig.ConfigurationProperties
import org.joda.time.Period
import org.joda.time.format.PeriodFormat
import personal.kostera.model.GitRepositoryState
import personal.kostera.model.HealthStatus
import personal.kostera.model.HealthStatusEnum

class HealthService(
    private val startedAt: Long = System.currentTimeMillis(),
    private val currentTimeSupplier: () -> Long = { System.currentTimeMillis() }
) {
    private val gitConfig
        get() = ConfigurationProperties.fromResource("git.properties")

    fun getHealthStatus(): HealthStatus = HealthStatus(
        status = HealthStatusEnum.GREEN,
        gitRepositoryState = GitRepositoryState(gitConfig),
        uptime = PeriodFormat.getDefault()
            .print(Period(currentTimeSupplier.invoke() - startedAt))
    )
}