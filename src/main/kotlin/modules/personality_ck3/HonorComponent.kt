package heraclius.modules.personality_ck3

//荣誉
class HonorComponent(value: Float) : CK3InnerPersonalityComponent(value) {
    override fun getAdjectives(): Class<out CK3PersonalityDescriptionComponent> {
        return HonorAdjectiveComponent::class.java
    }

    override fun getNouns(): Class<out CK3PersonalityDescriptionComponent> {
        return HonorNounsComponent::class.java
    }
}
