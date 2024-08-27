package heraclius.core.ecs

import heraclius.core.ReflectionReactor
import heraclius.core.Singleton
import heraclius.core.injectify.InstanceFactory
import org.reflections.Reflections

/**
 * ECS 反射处理器，用于自动创建 EntitySystem 的实例
 */
class ECSReflectionReactor : ReflectionReactor() {
    /**
     * 反射创建所有 EntitySystem 子类的实例
     * @param reflections Reflections 对象，用于扫描类路径中的子类
     */
    override fun doReact(reflections: Reflections) {
        // 获取所有 EntitySystem 的子类
        val systemClasses = reflections.getSubTypesOf(EntitySystem::class.java)
        // 遍历所有系统类，并创建或获取其实例注册为单例
        for (systemClass in systemClasses) {
            val instance = InstanceFactory.getOrCreateInstance(systemClass)
            Singleton.register(instance)
        }
    }
}
