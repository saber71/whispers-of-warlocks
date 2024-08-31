package heraclius.modules.personality_ck3

class VitalityNounsComponent(value: Int) : CK3PersonalityDescriptionComponent(value) {
    override fun describe(value: Int): String {
        return if (value >= 76) "活力充沛者"
        else if (value >= 1) "精力旺盛者"
        else if (value >= 0) ""
        else if (value > -76) "疲惫者"
        else "无力者"

    }
}
