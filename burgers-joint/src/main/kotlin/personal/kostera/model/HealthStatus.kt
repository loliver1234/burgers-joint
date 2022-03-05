package personal.kostera.model

import com.natpryce.konfig.ConfigurationProperties
import com.natpryce.konfig.Key
import com.natpryce.konfig.stringType

enum class HealthStatusEnum {
    RED,
    YELLOW,
    GREEN
}

class GitRepositoryState(gitConfig: ConfigurationProperties) {
    val commitId = gitConfig[Key("git.commit.id", stringType)]
    val commitIdAbbrev = gitConfig[Key("git.commit.id.abbrev", stringType)]
}

data class HealthStatus(
    var status: HealthStatusEnum,
    var uptime: String,
    var gitRepositoryState: GitRepositoryState
)