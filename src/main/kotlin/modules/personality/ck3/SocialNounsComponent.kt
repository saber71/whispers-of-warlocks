package heraclius.modules.personality.ck3

class SocialNounsComponent(value: Int) : CK3PersonalityDescriptionComponent(value) {
    override fun describe(value: Int): String {
        return if (value >= 76) "社交达人"
        else if (value >= 1) "君子"
        else if (value >= 0) ""
        else if (value > -76) "粗鲁之人"
        else "无礼之人"

    }
}
