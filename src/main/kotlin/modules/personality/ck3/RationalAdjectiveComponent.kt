package heraclius.modules.personality.ck3

class RationalAdjectiveComponent(value: Int) : CK3PersonalityDescriptionComponent(value) {
    override fun describe(value: Int): String {
        return if (value >= 76) "审时度势的"
        else if (value >= 1) "理性的"
        else if (value >= 0) ""
        else if (value > -76) "没有理性的"
        else "胡言乱语的"
    }
}
