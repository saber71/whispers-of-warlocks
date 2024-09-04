package heraclius.modules.personality.ck3

import heraclius.core.ecs.EntityComponentDescriptor

@EntityComponentDescriptor("怜悯")
class PityComponent(value: Number) : CK3InnerPersonalityComponent(value) {
    override fun getAdjectives(): Class<out CK3PersonalityDescriptionComponent> {
        return PityAdjectiveComponent::class.java
    }

    override fun getNouns(): Class<out CK3PersonalityDescriptionComponent> {
        return PityNounsComponent::class.java
    }
}
