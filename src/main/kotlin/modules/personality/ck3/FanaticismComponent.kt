package heraclius.modules.personality.ck3

import heraclius.core.ecs.EntityComponentDescriptor

@EntityComponentDescriptor("狂热")
class FanaticismComponent(value: Number) : CK3InnerPersonalityComponent(value) {
    override fun getAdjectives(): Class<out CK3PersonalityDescriptionComponent> {
        return FanaticismAdjectiveComponent::class.java
    }

    override fun getNouns(): Class<out CK3PersonalityDescriptionComponent> {
        return FanaticismNounsComponent::class.java
    }
}
