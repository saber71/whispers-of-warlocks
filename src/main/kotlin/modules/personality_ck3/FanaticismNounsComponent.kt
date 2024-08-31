package heraclius.modules.personality_ck3

class FanaticismNounsComponent(value: Int) : CK3PersonalityDescriptionComponent(value) {
    override fun describe(value: Int): String {
        return if (value >= 76) "狂信者"
        else if (value >= 1) "信徒"
        else if (value >= 0) ""
        else if (value > -76) "背信者"
        else "无信者"
    }
}
