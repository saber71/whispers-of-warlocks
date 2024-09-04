package heraclius.modules.personality.pokemon

import heraclius.core.ecs.EntityComponentDescriptor
import heraclius.core.value.Value

@EntityComponentDescriptor("InnerAttack")
class InnerAttackFactor(value: Value<Number>) :
    PokemonInnerPersonalityComponent(value) {
}
