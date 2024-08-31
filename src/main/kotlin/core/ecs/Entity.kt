package heraclius.core.ecs

import heraclius.core.IDGenerator
import heraclius.core.Symbols
import heraclius.core.datastore.DataStore
import heraclius.core.datastore.DataStoreKey

/**
 * 实体类，用于管理实体组件
 * 实体是 ECS (Entity-Component-System) 架构中的基本单位，包含一个或多个组件
 *
 * @param components 初始化时添加的实体组件数组
 */
open class Entity(vararg components: EntityComponent<*>) : DataStoreKey<Any>(Symbols.of(IDGenerator.get().toString())) {
    // 数据存储对象，用于管理实体的组件
    protected val dataStore = DataStore()

    // 实体组件列表
    private val _components = ArrayList<EntityComponent<*>>()

    // 获取实体组件列表
    val components: List<EntityComponent<*>> get() = this._components.toList()

    // 初始化时添加传入的组件
    init {
        addComponent(*components)
    }

    /**
     * 替换实体组件
     *
     * 该函数的目的是在实体上替换一个组件它首先移除现有的组件，然后添加新的组件
     * 这种替换操作可能在需要更新实体的某些特性和功能时非常有用通过这种方式，
     * 可以确保实体只包含一个特定类型的最新组件
     *
     * @param entityComponent 要替换的实体组件这个泛型参数表示它可以是任何类型的EntityComponent子类
     */
    fun replaceComponent(entityComponent: EntityComponent<*>) {
        removeComponent(entityComponent)
        addComponent(entityComponent)
    }

    /**
     * 添加一个或多个实体组件到实体中
     *
     * @param components 要添加的实体组件数组
     * @return 返回实体本身，支持链式调用
     * @throws RuntimeException 如果尝试添加已存在的组件，将抛出运行时异常
     */
    fun addComponent(vararg components: EntityComponent<*>): Entity {
        for (component in components) {
            if (dataStore.has(component)) {
                throw RuntimeException("Component $component already exists")
            }
            dataStore.set(component.javaClass, component)
            this._components.add(component)
        }
        return this
    }

    /**
     * 从实体中移除一个或多个实体组件
     *
     * @param components 要移除的实体组件数组
     * @return 返回实体本身，支持链式调用
     */
    fun removeComponent(vararg components: EntityComponent<*>): Entity {
        for (component in components) {
            dataStore.remove(component.javaClass)
            this._components.remove(component)
        }
        return this
    }

    /**
     * 检查实体是否具有指定类型的组件
     *
     * @param componentClass 组件类，用于检查实体是否包含此类型的组件
     * @return Boolean值，如果实体包含指定类型的组件则为true，否则为false
     */
    fun hasComponent(componentClass: Class<out EntityComponent<*>>): Boolean {
        return dataStore.has(componentClass)
    }

    /**
     * 获取实体中特定类型的组件
     *
     * @param componentClass 要获取的组件类型
     * @return 返回指定类型的组件实例，如果没有找到则返回 null
     */
    fun <E : EntityComponent<*>> getComponent(componentClass: Class<out E>): E? {
        return dataStore.get(componentClass)
    }

    /**
     * 获取实体组件的值
     *
     * 该函数的目的是针对实体组件管理进行优化，通过泛型限制，只允许继承自EntityComponent的组件参与操作
     * 这种设计既保证了类型安全，又提供了必要的灵活性
     *
     * @param <V> 组件存储的值的类型，表明组件中可以包含任何类型的值
     * @param <E> 继承自EntityComponent的泛型类型，确保操作的组件具有特定的结构和行为
     * @param componentClass 实体组件的类，用于指定哪个组件的值需要被获取
     * @return 返回组件的值，如果组件不存在或值未设置，则返回null
     */
    fun <V, E : EntityComponent<V>> getComponentValue(componentClass: Class<out E>): V? {
        return this.getComponent(componentClass)?.value()
    }

    /**
     * 与 `getComponent` 类似，但如果组件不存在则会抛出异常
     *
     * @param componentClass 要获取的组件类型
     * @return 返回指定类型的组件实例
     * @throws NoSuchElementException 如果组件不存在，将抛出 NoSuchElementException 异常
     */
    fun <E : EntityComponent<*>> fetchComponent(componentClass: Class<out E>): E {
        return dataStore.fetch(componentClass)
    }

    /**
     * 泛型方法，用于获取实体组件的值
     *
     * @param <V> 组件值的类型
     * @param <E> 组件类型，必须是EntityComponent的子类或实现类
     * @param componentClass 组件类型的类对象，用于指定要获取的组件
     * @return 返回组件的值
     *
     * 它首先调用fetchComponent方法获取组件实例，然后调用该实例的getValue方法来返回组件的值
     */
    fun <V, E : EntityComponent<V>> fetchComponentValue(componentClass: Class<out E>): V {
        return this.fetchComponent(componentClass).value()
    }

    // 获取实体的符号
    protected fun symbol(): Symbols.Symbol {
        return id() as Symbols.Symbol
    }
}
