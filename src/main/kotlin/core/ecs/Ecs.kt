package heraclius.core.ecs

import heraclius.core.datastore.DataStore

object Ecs {
    private val dataStore = DataStore()
    var systemList: List<EntitySystem> = emptyList()

    fun <V : Entity> getAll(clazz: Class<V>): List<V> {
        @Suppress("UNCHECKED_CAST")
        return dataStore.getAll(clazz) as List<V>
    }

    fun <V : Entity> first(clazz: Class<V>): V {
        return this.getAll(clazz)[0]
    }

    fun <E : Entity> createEntity(cls: Class<E>, vararg components: EntityComponent): E {
        val entity = cls.getDeclaredConstructor().newInstance(*components)
        dataStore.put(entity)
        return entity
    }
}
