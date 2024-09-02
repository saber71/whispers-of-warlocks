package heraclius.core.value

open class Value<V>(protected var `val`: V) {
    val content: V get() = `val`
}
