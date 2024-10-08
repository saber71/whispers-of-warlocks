package heraclius.modules.personality.ck3

import heraclius.core.ecs.EntityComponentDescriptor

@EntityComponentDescriptor("贪婪")
class GreedComponent(value: Number) : CK3InnerPersonalityComponent(value) {
    override fun getAdjectives(): Class<out CK3PersonalityDescriptionComponent> {
        return GreedAdjectiveComponent::class.java
    }

    override fun getNouns(): Class<out CK3PersonalityDescriptionComponent> {
        return GreedNounsComponent::class.java
    }
}
