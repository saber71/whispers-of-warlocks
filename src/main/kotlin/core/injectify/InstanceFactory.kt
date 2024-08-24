package heraclius.core.injectify

import heraclius.getAnnotationValue
import org.reflections.Reflections


/**
 * 包装了Reflections实例，用于方便地进行反射操作。
 */
class InstanceFactory(packageName: String) {
    /**
     * 创建Reflections实例，用于进行反射操作
     */
    private val reflections: Reflections = Reflections(packageName)

    /**
     * 存储已实例化的类的映射，用于缓存实例化的对象。
     */
    private val instanceMap: MutableMap<Class<*>, Any> = HashMap()

    /**
     * 存储正在实例化的类的集合，用于检测循环依赖。
     */
    private val instancing = HashSet<Class<*>>()

    /**
     * 获取指定类的实例。
     * 如果该类尚未实例化，则尝试通过反射创建其实例，并检查循环依赖。
     *
     * @param cls 要获取实例的类。
     * @return 类的实例。
     * @throws RuntimeException 如果检测到循环依赖。
     */
    fun <V> getInstance(cls: Class<V>): V {
        var instance = instanceMap.get(cls)
        if (instance == null) {
            if (instancing.contains(cls)) {
                throw RuntimeException("Circular dependency detected: $cls")
            }
            instancing.add(cls)
            val constructor = cls.getDeclaredConstructor()
            val parameters = constructor.parameters.map { getInstance(it.javaClass) }
            instance = constructor.newInstance(*parameters.toTypedArray())
            val annotation =
                getAnnotationValue(cls, Injectable::class.java) ?: throw RuntimeException("$cls is not injectable")
            if (annotation.singleton) {
                instanceMap[cls] = instance as Any
            }
            instancing.remove(cls)
        }
        @Suppress("UNCHECKED_CAST")
        return instance as V
    }

    /**
     * 获取具有指定注解的所有类。
     *
     * @param annotation 指定的注解类型。
     * @return 包含该注解的所有类的列表。
     */
    fun getClassesByAnnotation(annotation: Class<out Annotation>): List<Class<*>> {
        return reflections.getTypesAnnotatedWith(annotation).toList()
    }

    /**
     * 获取指定类的所有子类。
     *
     * @param cls 指定的类。
     * @return 该类的所有子类的列表。
     */
    fun <V> getClasses(cls: Class<out V>): List<Class<out V>> {
        return reflections.getSubTypesOf(cls).toList()
    }
}
