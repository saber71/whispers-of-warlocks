package heraclius.modules.personality_ck3

//报复
class VengeanceComponent(value: Float) : CK3InnerPersonalityComponent(value) {
    override fun getAdjectives(): Class<out CK3PersonalityDescriptionComponent> {
        return VengeanceAdjectiveComponent::class.java
    }

    override fun getNouns(): Class<out CK3PersonalityDescriptionComponent> {
        return VengeanceNounsComponent::class.java
    }
}
