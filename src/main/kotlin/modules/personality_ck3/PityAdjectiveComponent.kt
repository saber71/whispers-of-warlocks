package heraclius.modules.personality_ck3

class PityAdjectiveComponent(value: Int) : CK3PersonalityDescriptionComponent(value) {
    override fun describe(value: Int): String {
        return if (value >= 76) "仁慈的"
        else if (value >= 1) "富有同情心的"
        else if (value >= 0) ""
        else if (value > -76) "铁石心肠的"
        else "邪恶的"
    }
}
