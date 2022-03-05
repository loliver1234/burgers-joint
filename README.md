# burgers-joint
Welcome to Burgers Joint!

What is it? Everyone creates a bookstore as an example of given technology, so I decided to make a service that accepts daily orders from a burgers place that can be used inside an office.

It's basically a simple REST micro-service example based on Java 17 and kotlin technology stack. The code base aims to mostly use functional approach. In future I might add a presentation layer using React.

Following technologies used:
- [Ktor](https://ktor.io/) - as server layer
- [Kotlin exposed](https://github.com/JetBrains/Exposed) with [HikariCp](https://github.com/brettwooldridge/HikariCP) and [PostgreSQL](https://www.postgresql.org/) - as database layer
- [Redis](https://redis.io/) with [Redisson](https://redisson.org/) - used as a cache layer
- [RabbitMq](https://www.rabbitmq.com/) - used for notifications
- [Docker](https://docs.docker.com/engine/) with [Docker-compose](https://docs.docker.com/compose/) and [Jib](https://github.com/GoogleContainerTools/jib) - user for containers and deployment
- [RestAssured](https://rest-assured.io/) with [H2](https://www.h2database.com/html/main.html) - as testing tools
- [ArrowKt](https://arrow-kt.io/) - as functional programming library
- [apibluepint](https://apiblueprint.org/) - as documentation tool
- [prometheus](https://prometheus.io/) with [grafana](https://grafana.com/) - as monitoring tools
- [SonarQube](https://www.sonarqube.org/) - as static code analysis tool

How it works?
- Service exposes REST API:
    - Accepts burger orders
    - Returns current menu
    - Returns list of current orders
    - Allows to change order state (payment status)
- The burger orders are accepted each day between 7 am and 11 am
- The menu is read from external service each day as a first request for the menu occurres - then it is cached in redis with expiration date
- There is no authentication layer, each user can update other users order payment status
- At the end of the orders time window the message notification is sent on rabbitmq with full orders list