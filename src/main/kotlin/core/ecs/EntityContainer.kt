package heraclius.core.ecs

/**
 * 实体容器类，继承自Entity类，用于管理一组实体
 * 该类允许添加、检查和删除子实体，常用于场景或实体树的管理
 *
 * @param vararg components 实体容器初始化时可接收一个或多个实体组件
 */
open class EntityContainer(vararg components: EntityComponent<*>) : Entity(*components) {
    // 存储子实体的列表
    private val _entities = ArrayList<Entity>()

    /**
     * 获取所有子实体的只读列表
     *
     * @return 子实体的只读列表
     */
    val children: List<Entity> get() = this._entities.toList()

    /**
     * 向实体容器中添加一个或多个子实体
     * 如果尝试添加一个已经存在的子实体，将抛出运行时异常
     *
     * @param vararg entities 一个或多个要添加的子实体
     * @throws RuntimeException 如果某个实体已经作为子实体存在，则抛出此异常
     */
    fun addChild(vararg entities: Entity) {
        for (entity in entities) {
            if (this.hasChild(entity)) throw RuntimeException("Entity with id ${entity.id()} already exists")
            this._entities.add(entity)
            this.dataStore.put(entity)
        }
    }

    /**
     * 检查实体容器是否包含指定的子实体
     *
     * @param entity 要检查的子实体
     * @return Boolean 表示是否包含该子实体
     */
    fun hasChild(entity: Entity): Boolean {
        return this.dataStore.has(entity.id())
    }

    /**
     * 从实体容器中移除指定的子实体
     * 如果尝试移除一个不存在的子实体，则不执行任何操作
     *
     * @param entity 要移除的子实体
     */
    fun removeChild(entity: Entity) {
        if (!this.hasChild(entity)) return
        this._entities.remove(entity)
        this.dataStore.remove(entity.id())
    }

    /**
     * 获取指定类型实体的子项列表
     *
     * @param cls 实体类的类型，必须是Entity的子类
     * @param <V>  实体类的类型变量
     * @return 返回一个类型为V的列表，包含数据存储中所有实例
     *
     * 此函数通过委托给数据存储（DataStore）来获取所有指定类型的实体对象
     * 它利用泛型来确保灵活性和类型安全，只允许Entity的子类被查询
     */
    fun <V : Entity> getChildren(cls: Class<V>): List<V> {
        return dataStore.getAll(cls)
    }
}
