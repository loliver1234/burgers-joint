package personal.kostera.component

import com.natpryce.konfig.ConfigurationProperties
import org.joda.time.Period
import org.joda.time.format.PeriodFormat
import personal.kostera.model.GitRepositoryState
import personal.kostera.model.HealthStatus
import personal.kostera.model.HealthStatusEnum
import java.time.Duration
import java.time.LocalDateTime

class HealthService {

    private val startedAt = LocalDateTime.now()
    private val gitConfig
        get() = ConfigurationProperties.fromResource("git.properties")

    fun getHealthStatus(): HealthStatus = HealthStatus(
        status = HealthStatusEnum.GREEN,
        gitRepositoryState = GitRepositoryState(gitConfig),
        uptime = PeriodFormat.getDefault()
            .print(Period(Duration.between(startedAt, LocalDateTime.now()).toMillis()))
    )
}