package heraclius.modules.personality_ck3

//胆量
class CourageComponent(value: Number) : CK3InnerPersonalityComponent(value) {
    override fun getAdjectives(): Class<out CK3PersonalityDescriptionComponent> {
        return CourageAdjectiveComponent::class.java
    }

    override fun getNouns(): Class<out CK3PersonalityDescriptionComponent> {
        return CourageNounsComponent::class.java
    }
}
