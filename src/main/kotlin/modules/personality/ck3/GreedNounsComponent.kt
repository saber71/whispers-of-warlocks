package heraclius.modules.personality.ck3

class GreedNounsComponent(value: Int) : CK3PersonalityDescriptionComponent(value) {
    override fun describe(value: Int): String {
        return if (value >= 76) "掠夺者"
        else if (value >= 1) "吝啬鬼"
        else if (value >= 0) ""
        else if (value > -76) "循规蹈矩者"
        else "跟屁虫"
    }
}
