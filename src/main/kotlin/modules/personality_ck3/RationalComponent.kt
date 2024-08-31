package heraclius.modules.personality_ck3

//理性
class RationalComponent(value: Float) : CK3InnerPersonalityComponent(value) {
    override fun getAdjectives(): Class<out CK3PersonalityDescriptionComponent> {
        return RationalAdjectiveComponent::class.java
    }

    override fun getNouns(): Class<out CK3PersonalityDescriptionComponent> {
        return RationalNounsComponent::class.java
    }
}
