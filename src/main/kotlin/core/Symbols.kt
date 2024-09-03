package heraclius.core

import heraclius.core.value.MutableValue
import heraclius.core.value.Value
import java.lang.ref.WeakReference
import java.util.*

/**
 * 符号管理对象，用于创建和管理符号与其描述之间的映射。
 */
object Symbols {
    // 使用WeakHashMap存储符号和描述的映射关系，允许GC回收不再使用的符号。
    private val symbolMapDescription = WeakHashMap<Symbol, String?>()

    // 使用WeakHashMap存储描述和符号的映射关系，允许GC回收不再使用的描述。
    private val descriptionMapSymbol = WeakHashMap<String?, WeakReference<Symbol>>()

    /**
     * 根据给定的描述和值创建或获取一个符号
     *
     * 此函数的作用是将一个描述和一个值关联起来，封装成一个符号对象如果已经存在与描述相同的符号，则直接返回该符号；
     * 否则，根据给定的值创建一个新的值实例（如果值不是Value的实例，则将其转换为Value），然后创建并返回一个新的符号
     *
     * @param description 符号的描述，可能为null
     * @param value 与符号关联的值，可能为null
     * @return 返回一个符号实例，不会为null
     */
    fun use(description: String?, value: Any? = null): Symbol {
        // 尝试从描述创建一个符号如果成功且不为null，则直接返回这个符号
        val symbol = from(description)
        if (symbol != null) return symbol

        // 初始化值实例变量
        var valueInstance: Value<*>? = null
        // 如果值不为null，根据值创建或直接使用值实例
        if (value != null) {
            // 如果值不是Value的实例，将其转换为Value，否则直接使用值
            valueInstance = if (value !is Value<*>) Value(value)
            else value
        }

        // 创建并返回一个新的符号，关联给定的描述和值实例
        return of(description, valueInstance)
    }

    /**
     * 使用或创建一个可变的符号
     *
     * 此函数通过给定的描述和值参数，创建或获取一个可变的符号实例
     * 如果值参数已经是一个Value的实例，则直接使用它；否则，将值包装在一个可变值对象中
     * 然后调用`use`函数来使用或创建所需的符号
     *
     * @param description 符号的描述信息，可以为空
     * @param value 符号的值，可以是任何类型的实例，也可以为空
     * @return 返回一个Symbol对象，表示被使用或创建的符号
     */
    fun useMutable(description: String?, value: Any? = null): Symbol {
        var valueInstance: Value<*>? = null
        // 如果值不为空，根据值是否已经是Value的实例来决定如何处理
        if (value != null) {
            // 如果值不是Value的实例，将其包装在一个可变值对象中
            // 如果已经是Value的实例，则直接使用
            valueInstance = if (value !is Value<*>) MutableValue(value)
            else value
        }
        // 调用`use`函数，使用或创建符号
        return use(description, valueInstance)
    }

    /**
     * 创建或获取一个符号，并将其与描述关联。
     *
     * @param description 符号的描述，可以为空。
     * @param symbol 如果提供，则使用此符号，否则创建一个新的符号。
     * @return 与描述关联的符号。
     * @throws RuntimeException 如果尝试重复使用相同的描述。
     */
    fun of(description: String? = null, value: Value<*>? = null, symbol: Symbol? = null): Symbol {
        var symbol1 = symbol
        if (symbol1 == null) symbol1 = Symbol(value) { symbolMapDescription[symbol1] }

        // 确保描述唯一性
        val oldSymbol = from(description)
        if (oldSymbol != null) throw RuntimeException("description has repeated")

        // 将描述与符号关联
        symbolMapDescription[symbol1] = description
        // 将符号与描述关联
        if (!description.isNullOrEmpty()) descriptionMapSymbol[description] = WeakReference(symbol1)
        return symbol1
    }

    /**
     * 根据描述获取符号。
     *
     * @param description 要查找的符号的描述。
     * @return 匹配的符号及其描述的Map.Entry，如果未找到则返回null。
     */
    fun from(description: String?): Symbol? {
        return descriptionMapSymbol[description]?.get()
    }

    /**
     * 符号类，持有符号的描述信息。
     *
     * @property descriptor 一个lambda表达式，用于延迟获取符号的描述。
     */
    class Symbol(private val valueInstance: Value<*>?, private val descriptor: (() -> String?)) {
        override fun toString(): String {
            // 调用descriptor获取描述，并处理可能的空值
            return "Symbol(${descriptor.invoke() ?: ""})"
        }

        /**
         * 获取一个泛型值对象。
         *
         * @param <V> 泛型参数类型。
         * @return 返回一个泛型值对象。
         * @throws RuntimeException 如果valueInstance为null，抛出运行时异常。
         */
        fun <V> value(): Value<V> {
            // 检查valueInstance是否为null，如果为null则抛出异常
            if (valueInstance == null) throw RuntimeException("value is null")

            // 抑制类型转换检查，将valueInstance转换为Value<V>类型并返回
            @Suppress("UNCHECKED_CAST")
            return valueInstance as Value<V>
        }

        /**
         * 获取一个泛型值对象。
         *
         * @param <V> 泛型参数类型。
         * @return 返回一个泛型值对象。
         * @throws RuntimeException 如果valueInstance为null，抛出运行时异常。
         */
        fun <V> mutableValue(): MutableValue<V> {
            // 检查valueInstance是否为null，如果为null则抛出异常
            if (valueInstance == null) throw RuntimeException("value is null")

            // 抑制类型转换检查，将valueInstance转换为MutableValue<V>类型并返回
            @Suppress("UNCHECKED_CAST")
            return valueInstance as MutableValue<V>
        }
    }
}
