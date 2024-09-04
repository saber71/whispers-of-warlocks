package heraclius.modules.personality.ck3

import heraclius.core.ecs.EntityComponentDescriptor

@EntityComponentDescriptor("胆量")
class CourageComponent(value: Number) : CK3InnerPersonalityComponent(value) {
    override fun getAdjectives(): Class<out CK3PersonalityDescriptionComponent> {
        return CourageAdjectiveComponent::class.java
    }

    override fun getNouns(): Class<out CK3PersonalityDescriptionComponent> {
        return CourageNounsComponent::class.java
    }
}
