package heraclius.core.ecs

import heraclius.core.Singleton
import heraclius.core.Symbols
import heraclius.core.datastore.DataStore
import org.reflections.Reflections

/**
 * Ecs对象提供了核心的实体组件系统功能，包括初始化、实体的获取和创建、以及系统列表的更新。
 */
object Ecs {
    // 数据存储对象，用于管理实体和组件
    private val dataStore = DataStore()

    // 存储所有实体系统的列表
    private var systemList: List<EntitySystem> = emptyList()

    /**
     * 初始化实体组件系统，通过反射获取所有实体系统类的实例。
     * @param reflections 用于获取实体系统类的反射工具。
     */
    fun init(reflections: Reflections) {
        val systemClasses = reflections.getSubTypesOf(EntitySystem::class.java)
        systemList = systemClasses.map { Singleton.get(it) }
    }

    /**
     * 根据描述获取实体。
     * @param symbolDescription 实体的描述符。
     * @return 对应的实体，如果找不到则返回null。
     */
    fun <V : Entity> get(symbolDescription: String?): V? {
        val symbol = Symbols.from(symbolDescription) ?: return null
        return dataStore.get(symbol)
    }

    /**
     * 根据描述获取实体，如果找不到抛出异常。
     * @param symbolDescription 实体的描述符。
     * @return 对应的实体。
     * @throws RuntimeException 如果找不到对应的实体。
     */
    fun <V> fetch(symbolDescription: String?): V {
        val symbol = Symbols.from(symbolDescription) ?: throw RuntimeException("symbol not found")
        return dataStore.fetch(symbol)
    }

    /**
     * 获取所有指定类型的实体。
     * @param clazz 实体的类型。
     * @return 包含所有指定类型实体的列表。
     */
    fun <V : Entity> getAll(clazz: Class<V>): List<V> {
        return dataStore.getAll(clazz)
    }

    /**
     * 获取指定类型实体的第一个实例。
     * @param clazz 实体的类型。
     * @return 第一个指定类型的实体实例。
     */
    fun <V : Entity> first(clazz: Class<V>): V {
        return this.getAll(clazz).first()
    }

    /**
     * 获取指定类型实体的最后一个实例。
     * @param clazz 实体的类型。
     * @return 最后一个指定类型的实体实例。
     */
    fun <V : Entity> last(clazz: Class<V>): V {
        return this.getAll(clazz).last()
    }

    /**
     * 创建一个带有指定组件的实体。
     * @param cls 实体的类。
     * @param components 实体的一个或多个组件。
     * @return 创建的实体实例。
     */
    fun <E : Entity> createEntity(cls: Class<E>, vararg components: EntityComponent<*>): E {
        val constructor = cls.getConstructor(*components.map { it.javaClass }.toTypedArray())
        val entity = constructor.newInstance(*components)
        dataStore.put(entity)
        return entity
    }

    // 创建一个带有指定组件的实体，并返回Ecs对象以支持链式调用。
    fun <E : Entity> new(cls: Class<E>, vararg components: EntityComponent<*>): Ecs {
        createEntity(cls, *components)
        return this
    }

    /**
     * 更新所有实体系统。
     * 依次调用每个实体系统的更新方法。
     */
    fun tick() {
        systemList.forEach { it.update() }
    }
}
