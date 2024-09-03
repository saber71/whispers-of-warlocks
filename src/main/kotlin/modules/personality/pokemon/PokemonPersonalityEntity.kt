package heraclius.modules.personality.pokemon

import heraclius.core.ecs.EntityComponent
import heraclius.modules.personality.PersonalityEntity

class PokemonPersonalityEntity(vararg components: EntityComponent<*>) : PersonalityEntity(*components) {
}
