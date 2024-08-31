package heraclius.core.ecs

abstract class EntityComponent<T>(protected var `val`: T) {
    fun value(): T {
        return `val`
    }
}
