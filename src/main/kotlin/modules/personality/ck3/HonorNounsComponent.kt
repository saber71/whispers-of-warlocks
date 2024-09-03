package heraclius.modules.personality.ck3

class HonorNounsComponent(value: Int) :
    CK3PersonalityDescriptionComponent(value) {
    override fun describe(value: Int): String {
        return if (value >= 76) "典范"
        else if (value >= 1) "表率"
        else if (value >= 0) ""
        else if (value > -76) "无赖"
        else "流氓"
    }
}
