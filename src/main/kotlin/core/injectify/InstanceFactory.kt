package heraclius.core.injectify

import heraclius.core.Singleton
import heraclius.getAnnotationValue


/**
 * 实例工厂类，实现依赖注入
 */
class InstanceFactory {
    /**
     * 存储已实例化的类的映射，用于缓存实例化的对象。
     */
    private val instanceMap: MutableMap<Class<*>, Any> = HashMap()

    /**
     * 存储正在实例化的类的集合，用于检测循环依赖。
     */
    private val instancing = HashSet<Class<*>>()

    /**
     * 获取或创建指定类的实例。
     * 如果该类尚未实例化，则尝试通过反射创建其实例，并检查循环依赖。
     * 如果类被标记为是单例的，则会将实例注册在Singleton中
     *
     * @param cls 要获取实例的类。
     * @return 类的实例。
     * @throws RuntimeException 如果检测到循环依赖。
     */
    fun <V> getOrCreateInstance(cls: Class<V>): V {
        var instance = instanceMap.get(cls)
        if (instance == null) {
            if (instancing.contains(cls)) {
                throw RuntimeException("Circular dependency detected: $cls")
            }
            instancing.add(cls)
            val constructor = cls.getDeclaredConstructor()
            val parameters = constructor.parameters.map { getOrCreateInstance(it.javaClass) }
            instance = constructor.newInstance(*parameters.toTypedArray())
            val annotation =
                getAnnotationValue(cls, Injectable::class.java) ?: throw RuntimeException("$cls is not injectable")
            if (annotation.singleton) {
                instanceMap[cls] = instance as Any
                Singleton.register(instance)
            }
            instancing.remove(cls)
        }
        @Suppress("UNCHECKED_CAST")
        return instance as V
    }
}
