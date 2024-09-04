package heraclius.core.ecs

import heraclius.Utils
import org.reflections.Reflections

/**
 * 实体组件工厂，用于根据注解自动创建实体组件实例
 */
object EntityComponentFactory {
    // 用于存储组件名称与对应类的映射关系
    private val nameMapCls = mutableMapOf<String, Class<*>>()

    /**
     * 初始化工厂，通过反射扫描所有带有EntityComponentDescriptor注解的类，并记录它们的名称映射
     *
     * @param reflections 用于反射扫描的工具类
     */
    fun init(reflections: Reflections) {
        // 获取所有带有EntityComponentDescriptor注解的类
        val classes = reflections.getTypesAnnotatedWith(EntityComponentDescriptor::class.java)
        for (clazz in classes) {
            // 获取类上的EntityComponentDescriptor注解实例
            val annotation = Utils.getAnnotationInstance(clazz, EntityComponentDescriptor::class.java) ?: continue
            // 遍历注解中的所有组件名称
            for (name in annotation.names) {
                // 检查是否有重复的组件名称
                if (nameMapCls.containsKey(name)) throw RuntimeException("Duplicate component name: $name")
                // 将组件名称映射到对应的类
                nameMapCls[name] = clazz
            }
        }
    }

    /**
     * 根据组件名称创建其实例
     *
     * @param name 组件的名称
     * @param args 用于创建组件实例的构造函数参数
     * @return 创建的组件实例
     * @V 实体组件的类型
     */
    fun <V : EntityComponent<*>> create(name: String, vararg args: Any): V {
        // 检查组件名称是否已映射到对应的类
        if (!nameMapCls.containsKey(name)) throw RuntimeException("Unknown component name: $name")
        val clazz = nameMapCls[name]!!
        // 使用 unchecked cast 将构造器实例化结果转换为正确的泛型类型
        @Suppress("UNCHECKED_CAST")
        return clazz.constructors[0].newInstance(*args) as V
    }
}
