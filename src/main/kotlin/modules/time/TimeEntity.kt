package heraclius.modules.time

import heraclius.core.ecs.Entity
import heraclius.core.ecs.EntityComponent

class TimeEntity(vararg components: EntityComponent<*>) : Entity(*components) {
}
