package heraclius.modules.personality.ck3

class VengeanceAdjectiveComponent(value: Int) : CK3PersonalityDescriptionComponent(value) {
    override fun describe(value: Int): String {
        return if (value >= 76) "记仇的"
        else if (value >= 1) "忿忿不平的"
        else if (value >= 0) ""
        else if (value > -76) "宽容的"
        else "宽宏大量的"
    }
}
