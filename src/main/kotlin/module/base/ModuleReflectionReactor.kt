package heraclius.module.base

import heraclius.core.ReflectionReactor
import heraclius.core.Singleton
import heraclius.core.injectify.InstanceFactory
import org.reflections.Reflections

/**
 * 模块反射反应器，用于处理和管理模块的反射操作。
 * 它会扫描并实例化所有继承自BaseModule的子类。
 */
class ModuleReflectionReactor : ReflectionReactor() {
    // 存储扫描到的模块实例
    private val moduleInstances = ArrayList<BaseModule>()

    /**
     * 执行反射处理操作。
     * 通过Reflections对象扫描并获取所有BaseModule的子类，并使用InstanceFactory来实例化它们。
     *
     * @param reflections Reflections对象，用于扫描类和子类。
     */
    override fun doReact(reflections: Reflections) {
        // 获取所有BaseModule的子类
        val moduleCls = reflections.getSubTypesOf(BaseModule::class.java)
        // 获取InstanceFactory的单例实例
        val instanceFactory = Singleton.get(InstanceFactory::class.java)
        // 遍历所有子类，实例化并添加到模块实例列表中
        for (cls in moduleCls) {
            val moduleInstance = instanceFactory.getOrCreateInstance(cls)
            moduleInstances.add(moduleInstance)
        }
    }

    // 获取所有模块实例
    fun getAllModules(): List<BaseModule> {
        return moduleInstances.toList()
    }
}
