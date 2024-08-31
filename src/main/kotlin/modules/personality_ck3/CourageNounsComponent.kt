package heraclius.modules.personality_ck3

class CourageNounsComponent(value: Int) : CK3PersonalityDescriptionComponent(value) {
    override fun describe(value: Int): String {
        return if (value >= 76) "冒险家"
        else if (value >= 1) "赌徒"
        else if (value >= 0) ""
        else if (value > -76) "懦夫"
        else "胆小鬼"
    }
}
