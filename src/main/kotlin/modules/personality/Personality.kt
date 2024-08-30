package heraclius.modules.personality

import heraclius.core.ecs.EntityComponent
import heraclius.core.ecs.EntityContainer

class Personality(vararg components: EntityComponent<*>) : EntityContainer(*components) {
}
