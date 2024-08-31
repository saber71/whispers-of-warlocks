package heraclius.modules.personality_ck3

//社交力
class SocialComponent(value: Float) : CK3InnerPersonalityComponent(value) {
    override fun getAdjectives(): Class<out CK3PersonalityDescriptionComponent> {
        return SocialAdjectiveComponent::class.java
    }

    override fun getNouns(): Class<out CK3PersonalityDescriptionComponent> {
        return SocialNounsComponent::class.java
    }
}
