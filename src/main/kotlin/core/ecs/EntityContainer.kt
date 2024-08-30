package heraclius.core.ecs

open class EntityContainer(vararg components: EntityComponent<*>) : Entity(*components) {
    private val _entities = ArrayList<Entity>()
    val children: List<Entity> get() = this._entities.toList()

    fun addChild(vararg entities: Entity) {
        for (entity in entities) {
            if (this.hasChild(entity)) throw RuntimeException("Entity with id ${entity.id()} already exists")
            this._entities.add(entity)
            this.dataStore.put(entity)
        }
    }

    fun hasChild(entity: Entity): Boolean {
        return this.dataStore.has(entity.id())
    }

    fun removeChild(entity: Entity) {
        if (!this.hasChild(entity)) return
        this._entities.remove(entity)
        this.dataStore.remove(entity.id())
    }
}
