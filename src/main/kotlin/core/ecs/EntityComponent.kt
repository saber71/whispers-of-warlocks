package heraclius.core.ecs

abstract class EntityComponent<T>(protected var `val`: T) {
    open fun value(): T {
        return `val`
    }
}
