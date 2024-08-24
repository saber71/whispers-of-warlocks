package heraclius.module.base

import heraclius.core.datastore.DataStore
import heraclius.core.dispatcher.Dispatcher
import heraclius.readProperties

/**
 * 基础模块类，所有模块的基类
 *
 * @property name 模块名称
 * @property description 模块描述
 */
abstract class BaseModule(val name: String, val description: String) {
    // 存储模块的数据
    val dataStore = DataStore()

    // 模块的调度器，用于管理模块内的任务调度
    val dispatcher = Dispatcher()

    // 依赖的其他模块列表，初始化为空列表
    private var dependencyModules = emptyList<BaseModule>()

    // 依赖于当前模块的其他模块集合，初始化为空集合
    private var dependentOnModules = HashSet<BaseModule>()

    // 是否正在安装标志，初始化为false
    private var isInstalling = false

    // 是否已安装标志，初始化为false
    private var isInstalled = false

    // 是否已卸载标志，初始化为false
    private var isUninstalled = false

    /**
     * 安装模块
     *
     * 检查模块是否已被安装，或正在安装中，然后递归地安装所有依赖模块
     * 最后执行模块自身的安装逻辑
     */
    fun install() {
        if (isInstalled) return
        if (isInstalling) throw RuntimeException("module $name is installing")
        isInstalling = true
        isUninstalled = false
        // 读取模块配置，获取依赖模块列表
        val modules = readProperties(this, BaseModule::class.java)
        dependencyModules = modules
        // 遍历依赖模块，更新依赖关系并递归安装
        for (module in modules) {
            module.dependentOnModules.add(this)
            module.install()
        }
        installImpl()
        isInstalling = false
    }

    /**
     * 卸载模块
     *
     * @param recursive 是否递归卸载依赖模块，默认为false
     *
     * 首先执行模块自身的卸载逻辑，然后清除数据存储和调度器
     * 如果recursive为true，还会递归卸载所有依赖于当前模块的模块
     */
    fun uninstall(recursive: Boolean = false) {
        if (isUninstalled) return
        isUninstalled = true
        isInstalled = false
        uninstallImpl()
        dataStore.clear()
        dispatcher.clear()
        // 递归卸载依赖模块
        if (recursive) {
            for (dependencyModule in dependencyModules) {
                dependencyModule.dependentOnModules.remove(this)
                if (dependencyModule.dependentOnModules.size == 0) {
                    dependencyModule.uninstall(true)
                }
            }
        }
    }

    /**
     * 模块具体的安装逻辑，由子类实现
     */
    protected abstract fun installImpl()

    /**
     * 模块具体的卸载逻辑，由子类实现
     */
    protected abstract fun uninstallImpl()
}
