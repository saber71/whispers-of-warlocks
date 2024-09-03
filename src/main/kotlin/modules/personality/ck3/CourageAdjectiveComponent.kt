package heraclius.modules.personality.ck3

class CourageAdjectiveComponent(value: Int) :
    CK3PersonalityDescriptionComponent(value) {
    override fun describe(value: Int): String {
        return if (value >= 76) "无畏的"
        else if (value >= 1) "大胆的"
        else if (value >= 0) ""
        else if (value > -76) "怯懦的"
        else "没骨气"
    }
}
