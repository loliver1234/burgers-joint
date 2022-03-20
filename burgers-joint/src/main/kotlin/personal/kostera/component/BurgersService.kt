package personal.kostera.component

import arrow.core.*
import org.joda.time.DateTime
import personal.kostera.model.Error
import personal.kostera.model.Order

class BurgersService(private val currentTimeSupplier: () -> Long = { System.currentTimeMillis() }) {

    suspend fun createOrder(requestList: List<Map<String, Any?>>): Either<Error, Order> =
        when (areOrdersOpen()) {
            true -> TODO()
            false -> Error.OrdersLocked(currentTime = currentTimeSupplier.invoke()).left()
        }

    private fun areOrdersOpen(): Boolean =
        DateTime(currentTimeSupplier.invoke()).hourOfDay().get() in 7..10

}