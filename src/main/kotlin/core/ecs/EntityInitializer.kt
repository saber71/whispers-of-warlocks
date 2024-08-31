package heraclius.core.ecs

/**
 * 实体初始化器的基类，用于在实体组件系统(ECS)中定义实体的初始化逻辑。
 */
abstract class EntityInitializer {
    /**
     * 初始化实体的方法。子类需要重写此方法以提供具体的初始化逻辑。
     */
    abstract fun init()
}
