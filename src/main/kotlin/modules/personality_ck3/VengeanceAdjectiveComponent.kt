package heraclius.modules.personality_ck3

class VengeanceAdjectiveComponent(value: Int) : CK3PersonalityDescriptionComponent(value) {
    override fun describe(value: Int): String {
        return if (value >= 76) "记仇的"
        else if (value >= 1) "忿忿不平的"
        else if (value >= 0) ""
        else if (value > -76) "宽容的"
        else "宽宏大量的"

        TODO("\t宽恕者\t\t怀柔者\t\t吵闹者\t\t复仇者 yet implemented")
    }
}
