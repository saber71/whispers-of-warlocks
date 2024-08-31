package heraclius.core

/**
 * 单例模式类，用于管理不同类的单例实例
 */
object Singleton {
    // 存储不同类的单例实例
    private val instances: MutableMap<Class<*>, Any> = HashMap()

    /**
     * 检查指定类型的实例是否存在于容器中
     *
     * @param clazz 要检查的类型类对象
     * @return 如果实例存在返回true，否则返回false
     */
    fun has(clazz: Class<*>): Boolean {
        return instances.containsKey(clazz)
    }

    /**
     * 获取指定类的单例实例
     * 如果该类的实例尚不存在，则创建一个新的实例并将其存储在instances映射中。注意新建的实例的构造函数必须是无参的
     *
     * @param cls 指定的类类型
     * @return 该类的单例实例
     * @throws RuntimeException 如果无法创建新实例
     */
    fun <T : Any> get(cls: Class<T>): T {
        // 检查是否已经存在该类的实例
        if (!instances.containsKey(cls)) {
            try {
                // 创建新实例并存储
                instances[cls] = cls.getDeclaredConstructor().newInstance()
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
        @Suppress("UNCHECKED_CAST")
        return instances[cls] as T
    }

    // 注册单例实例
    fun register(instance: Any) {
        instances[instance.javaClass] = instance
    }
}
