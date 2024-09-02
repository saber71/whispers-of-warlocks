package heraclius.core.value

import heraclius.core.dispatcher.Dispatcher

class MutableValue<V>(value: V) : Value<V>(value) {
    private val dispatcher = Dispatcher()

    fun mutate(newValue: V) {
        if (content != newValue) {
            val oldValue = content
            `val` = newValue
            dispatcher.dispatch(ValueChanged(oldValue, newValue))
        }
    }

    fun capture(listener: (ValueChanged<V>) -> Unit): Value<V> {
        @Suppress("UNCHECKED_CAST")
        dispatcher.handle(ValueChanged::class.java, listener as (ValueChanged<*>) -> Unit)
        return this
    }
}
