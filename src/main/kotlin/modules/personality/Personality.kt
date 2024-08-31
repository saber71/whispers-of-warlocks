package heraclius.modules.personality

import heraclius.core.ecs.Entity
import heraclius.core.ecs.EntityComponent

class Personality(vararg components: EntityComponent<*>) : Entity(*components) {
}
