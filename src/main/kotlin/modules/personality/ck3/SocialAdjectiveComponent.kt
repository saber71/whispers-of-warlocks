package heraclius.modules.personality.ck3

class SocialAdjectiveComponent(value: Int) : CK3PersonalityDescriptionComponent(value) {
    override fun describe(value: Int): String {
        return if (value >= 76) "八面玲珑的"
        else if (value >= 1) "有教养的"
        else if (value >= 0) ""
        else if (value > -76) "粗鲁的"
        else "无礼的"
    }
}
