package heraclius.modules.personality.pokemon

import heraclius.core.ecs.EntityComponentDescriptor
import heraclius.core.value.Value

@EntityComponentDescriptor("InnerSpecialDefense")
class InnerSpecialDefenseFactor(value: Value<Number>) : PokemonInnerPersonalityComponent(value) {
}
