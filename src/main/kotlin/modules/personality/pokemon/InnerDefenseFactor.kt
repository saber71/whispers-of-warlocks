package heraclius.modules.personality.pokemon

import heraclius.core.ecs.EntityComponentDescriptor
import heraclius.core.value.Value

@EntityComponentDescriptor("InnerDefense")
class InnerDefenseFactor(value: Value<Number>) : PokemonInnerPersonalityComponent(value) {
}
