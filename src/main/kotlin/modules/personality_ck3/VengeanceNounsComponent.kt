package heraclius.modules.personality_ck3

class VengeanceNounsComponent(value: Int) : CK3PersonalityDescriptionComponent(value) {
    override fun describe(value: Int): String {
        return if (value >= 76) "复仇者"
        else if (value >= 1) "吵闹者"
        else if (value >= 0) ""
        else if (value > -76) "怀柔者"
        else "宽恕者"
    }
}
