package heraclius.core.ecs

open class MutableEntityComponent<T>(value: T) : EntityComponent<T>(value) {
    fun setValue(value: T) {
        this.value = value
    }
}
