package heraclius.modules.personality_pokemon

import heraclius.core.ecs.EntityComponent
import heraclius.modules.personality.PersonalityEntity

class PokemonPersonalityEntity(vararg components: EntityComponent<*>) : PersonalityEntity(*components) {
}
