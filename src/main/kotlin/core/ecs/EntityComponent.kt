package heraclius.core.ecs

abstract class EntityComponent<T>(protected var value: T) {
    fun getValue(): T {
        return value
    }
}
