package heraclius.modules.personality.pokemon

import heraclius.core.ecs.EntityComponentDescriptor
import heraclius.core.value.Value

@EntityComponentDescriptor("InnerHP")
class InnerHPFactor(value: Value<Number>) : PokemonInnerPersonalityComponent(value) {
}
