package heraclius.modules.personality.pokemon

import heraclius.core.ecs.EntityComponentDescriptor
import heraclius.core.value.Value

@EntityComponentDescriptor("InnerSpeed")
class InnerSpeedFactor(value: Value<Number>) : PokemonInnerPersonalityComponent(value) {
}
