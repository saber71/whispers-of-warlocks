package heraclius.modules.personality.ck3

//理性
class RationalComponent(value: Number) : CK3InnerPersonalityComponent(value) {
    override fun getAdjectives(): Class<out CK3PersonalityDescriptionComponent> {
        return RationalAdjectiveComponent::class.java
    }

    override fun getNouns(): Class<out CK3PersonalityDescriptionComponent> {
        return RationalNounsComponent::class.java
    }
}
