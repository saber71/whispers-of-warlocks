package heraclius.core.ecs

import heraclius.core.datastore.DataStore

/**
 * 实体类，用于管理实体组件
 * 实体是 ECS (Entity-Component-System) 架构中的基本单位，包含一个或多个组件
 *
 * @param components 初始化时添加的实体组件数组
 */
class Entity(vararg components: EntityComponent) {
    // 数据存储对象，用于管理实体的组件
    private val dataStore = DataStore()

    // 初始化时添加传入的组件
    init {
        addComponent(*components)
    }

    /**
     * 添加一个或多个实体组件到实体中
     *
     * @param components 要添加的实体组件数组
     * @return 返回实体本身，支持链式调用
     * @throws RuntimeException 如果尝试添加已存在的组件，将抛出运行时异常
     */
    fun addComponent(vararg components: EntityComponent): Entity {
        for (component in components) {
            if (dataStore.has(component)) {
                throw RuntimeException("Component $component already exists")
            }
            dataStore.set(component.javaClass, component)
        }
        return this
    }

    /**
     * 从实体中移除一个或多个实体组件
     *
     * @param components 要移除的实体组件数组
     * @return 返回实体本身，支持链式调用
     */
    fun removeComponent(vararg components: EntityComponent): Entity {
        for (component in components) {
            dataStore.remove(component.javaClass)
        }
        return this
    }

    /**
     * 获取实体中特定类型的组件
     *
     * @param componentClass 要获取的组件类型
     * @return 返回指定类型的组件实例，如果没有找到则返回 null
     */
    fun getComponent(componentClass: Class<out EntityComponent>): EntityComponent? {
        return dataStore.get(componentClass)
    }

    /**
     * 与 `getComponent` 类似，但如果组件不存在则会抛出异常
     *
     * @param componentClass 要获取的组件类型
     * @return 返回指定类型的组件实例
     * @throws NoSuchElementException 如果组件不存在，将抛出 NoSuchElementException 异常
     */
    fun fetchComponent(componentClass: Class<out EntityComponent>): EntityComponent {
        return dataStore.fetch(componentClass)
    }
}
