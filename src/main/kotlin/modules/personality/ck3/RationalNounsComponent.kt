package heraclius.modules.personality.ck3

class RationalNounsComponent(value: Int) : CK3PersonalityDescriptionComponent(value) {
    override fun describe(value: Int): String {
        return if (value >= 76) "规划家"
        else if (value >= 1) "思想家"
        else if (value >= 0) ""
        else if (value > -76) "蠢货"
        else "疯子"
    }
}
