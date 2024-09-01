package heraclius.modules.personality

import heraclius.core.ecs.Entity
import heraclius.core.ecs.EntityComponent

open class PersonalityEntity(vararg components: EntityComponent<*>) : Entity(*components) {
}
