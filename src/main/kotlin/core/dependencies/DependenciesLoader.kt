package heraclius.core.dependencies

import heraclius.Utils
import heraclius.core.ReflectionReactor
import org.reflections.Reflections

/**
 * 依赖项加载器类，用于通过反射机制分析和处理类的依赖关系
 */
class DependenciesLoader : ReflectionReactor() {
    // 存储每个类及其对应的依赖类列表
    private val classDependencies = HashMap<Class<*>, List<Class<*>>>()

    /**
     * 根据反射实例来分析和加载依赖关系
     * @param reflections Reflections实例，用于获取带有特定注解的类
     */
    override fun doReact(reflections: Reflections) {
        // 获取所有带有Dependencies注解的类
        val classes = reflections.getTypesAnnotatedWith(Dependencies::class.java)
        for (clazz in classes) {
            // 获取类上Dependencies注解的实例
            val annotation = Utils.getAnnotationInstance(clazz, Dependencies::class.java)
            if (annotation != null) {
                // 获取注解中声明的依赖类数组
                val dependencies = annotation.classes
                // 将依赖类转换为Class对象的列表，并存储起来
                classDependencies[clazz] = dependencies.map { it.java }.toList()
            }
        }
    }

    /**
     * 获取指定类的依赖关系列表
     * @param clazz 要获取依赖关系的类
     * @return 返回依赖类列表，如果没有依赖关系，则返回空列表
     */
    fun getDependencies(clazz: Class<*>): List<Class<*>> {
        if (classDependencies.containsKey(clazz)) return classDependencies[clazz]!!
        // 获取指定类及其所有父类的列表
        val classes = Utils.getParentClasses(clazz)
        // 遍历类列表，查找是否有依赖关系
        for (cls in classes) {
            // 如果找到依赖关系，则返回依赖类列表
            if (classDependencies.containsKey(cls)) {
                val result = classDependencies[cls]!!
                if (cls != clazz) classDependencies[cls] = result
                return result
            }
        }
        // 如果没有找到依赖关系，则返回空列表
        return emptyList()
    }
}
