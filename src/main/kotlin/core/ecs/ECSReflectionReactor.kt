package heraclius.core.ecs

import heraclius.Utils
import heraclius.core.ReflectionReactor
import heraclius.core.dependencies.DependenciesLoader
import heraclius.core.injectify.InstanceFactory
import org.reflections.Reflections

/**
 * ECS 反射处理器，用于自动创建 EntitySystem 实例和执行初始化操作
 */
class ECSReflectionReactor : ReflectionReactor() {
    // 存储已经初始化的类
    private val initialized = HashSet<Class<*>>()

    // 存储正在初始化的类
    private val initializing = HashSet<Class<*>>()

    /**
     * 反射创建所有 EntitySystem 子类的实例
     * @param reflections Reflections 对象，用于扫描类路径中的子类
     */
    override fun doReactImpl(reflections: Reflections) {
        // 加载依赖项，确保能正确的加载依赖
        DependenciesLoader.doReact(reflections)
        // 初始化组件工厂
        EntityComponentFactory.init(reflections)
        // 获取所有 EntitySystem 的子类
        val systemClasses = reflections.getSubTypesOf(EntitySystem::class.java)
        // 获取所有 EntityInitializer 的子类
        val initializerClasses = reflections.getSubTypesOf(EntityInitializer::class.java)
        // 遍历所有系统类，并创建或获取其实例注册为单例
        systemClasses.forEach { InstanceFactory.getOrCreateInstance(it) }
        // 遍历所有初始化类，执行初始化操作
        initializerClasses.forEach { doInitial(it) }
        // 初始化 ECS 系统
        Ecs.init(reflections)
    }

    /**
     * 执行初始化操作的私有方法，用于避免循环依赖
     *
     * @param cls 要初始化的类，必须是EntityInitializer的子类
     * @throws RuntimeException 当检测到循环依赖时抛出运行时异常
     */
    private fun doInitial(cls: Class<out EntityInitializer>) {
        // 检查当前类是否已经初始化过，如果是，则直接返回
        if (initialized.contains(cls)) return
        // 检查当前类是否正在初始化中，如果是，则抛出循环依赖异常
        if (initializing.contains(cls)) throw RuntimeException("Circular dependency detected for class $cls")
        // 将当前类添加到初始化中的集合，以支持跨类初始化
        initializing.add(cls)
        // 加载当前类的依赖项
        val dependencies = DependenciesLoader.load(cls)
        for (dependencyCls in dependencies) {
            // 获取依赖项的所有父类
            val parentClasses = Utils.getParentClasses(dependencyCls)
            // 检查依赖项是否也是一个EntityInitializer的子类
            if (parentClasses.contains(EntityInitializer::class.java)) {
                // 递归调用doInitial来初始化依赖项
                @Suppress("UNCHECKED_CAST")
                doInitial(dependencyCls as Class<out EntityInitializer>)
            }
        }
        // 获取或创建当前类的实例
        val instance = InstanceFactory.getOrCreateInstance(cls)
        // 调用实例的初始化方法
        instance.init()
        // 从初始化中的集合中移除当前类，表示初始化完成
        initializing.remove(cls)
        // 将当前类添加到已初始化的集合中
        initialized.add(cls)
    }
}
