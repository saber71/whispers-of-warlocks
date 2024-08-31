package heraclius.modules.personality_ck3

class PityNounsComponent(value: Int) : CK3PersonalityDescriptionComponent(value) {
    override fun describe(value: Int): String {
        return if (value >= 76) "感同身受者"
        else if (value >= 1) "利他主义者"
        else if (value >= 0) ""
        else if (value > -76) "禽兽"
        else "恶棍"
    }
}
