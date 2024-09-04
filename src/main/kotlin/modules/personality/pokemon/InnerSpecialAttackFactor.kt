package heraclius.modules.personality.pokemon

import heraclius.core.ecs.EntityComponentDescriptor
import heraclius.core.value.Value

@EntityComponentDescriptor("InnerSpecialAttack")
class InnerSpecialAttackFactor(value: Value<Number>) : PokemonInnerPersonalityComponent(value) {
}
