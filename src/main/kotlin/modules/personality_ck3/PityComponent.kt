package heraclius.modules.personality_ck3

//怜悯
class PityComponent(value: Float) : CK3InnerPersonalityComponent(value) {
    override fun getAdjectives(): Class<out CK3PersonalityDescriptionComponent> {
        return PityAdjectiveComponent::class.java
    }

    override fun getNouns(): Class<out CK3PersonalityDescriptionComponent> {
        return PityNounsComponent::class.java
    }
}
