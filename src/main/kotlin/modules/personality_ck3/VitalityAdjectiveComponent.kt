package heraclius.modules.personality_ck3

class VitalityAdjectiveComponent(value: Int) : CK3PersonalityDescriptionComponent(value) {
    override fun describe(value: Int): String {
        return if (value >= 76) "精力充沛的"
        else if (value >= 1) "充满活力的"
        else if (value >= 0) ""
        else if (value > -76) "疲惫的"
        else "无力的"
    }
}
