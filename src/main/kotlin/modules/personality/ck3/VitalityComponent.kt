package heraclius.modules.personality.ck3

import heraclius.core.ecs.EntityComponentDescriptor

@EntityComponentDescriptor("精力")
class VitalityComponent(value: Number) : CK3InnerPersonalityComponent(value) {
    override fun getAdjectives(): Class<out CK3PersonalityDescriptionComponent> {
        return VitalityAdjectiveComponent::class.java
    }

    override fun getNouns(): Class<out CK3PersonalityDescriptionComponent> {
        return VitalityNounsComponent::class.java
    }
}
