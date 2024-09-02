package heraclius.core.value

import heraclius.core.Symbols

object Values {
    private val map = mutableMapOf<Symbols.Symbol, Value<*>>()

    fun <V> ofImmutable(value: V, name: String?): Value<V> {
        @Suppress("UNCHECKED_CAST")
        return createValue(value, Symbols.use(name), Value::class.java) as Value<V>
    }

    fun <V> ofMutable(value: V, name: String = ""): MutableValue<V> {
        @Suppress("UNCHECKED_CAST")
        return createValue(value, Symbols.use(name), MutableValue::class.java) as MutableValue<V>
    }

    private fun <V, ValueType : Value<*>> createValue(
        value: V,
        symbol: Symbols.Symbol,
        valueCls: Class<ValueType>
    ): ValueType {
        @Suppress("UNCHECKED_CAST")
        if (map.containsKey(symbol)) return map[symbol] as ValueType
        @Suppress("UNCHECKED_CAST")
        val result = valueCls.constructors[0].newInstance(value) as ValueType
        map[symbol] = result
        return result
    }
}
