package heraclius.core.ecs

import heraclius.core.Singleton
import heraclius.core.datastore.DataStore
import org.reflections.Reflections

object Ecs {
    private val dataStore = DataStore()
    private var systemList: List<EntitySystem> = emptyList()

    fun init(reflections: Reflections) {
        val systemClasses = reflections.getSubTypesOf(EntitySystem::class.java)
        systemList = systemClasses.map { Singleton.get(it) }
    }

    fun <V : Entity> getAll(clazz: Class<V>): List<V> {
        @Suppress("UNCHECKED_CAST")
        return dataStore.getAll(clazz) as List<V>
    }

    fun <V : Entity> first(clazz: Class<V>): V {
        return this.getAll(clazz).first()
    }

    fun <V : Entity> last(clazz: Class<V>): V {
        return this.getAll(clazz).last()
    }

    fun <E : Entity> createEntity(cls: Class<E>, vararg components: EntityComponent<*>): E {
        val entity = cls.getDeclaredConstructor().newInstance(*components)
        dataStore.put(entity)
        return entity
    }

    fun tick() {
        systemList.forEach { it.update() }
    }
}
