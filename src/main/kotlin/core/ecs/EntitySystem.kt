package heraclius.core.ecs

// 系统，单例
abstract class EntitySystem {
    open fun init() {}

    abstract fun update()
}
