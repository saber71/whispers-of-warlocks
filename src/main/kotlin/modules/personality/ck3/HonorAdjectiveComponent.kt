package heraclius.modules.personality.ck3

class HonorAdjectiveComponent(value: Int) : CK3PersonalityDescriptionComponent(value) {
    override fun describe(value: Int): String {
        return if (value >= 76) "刚正不阿的"
        else if (value >= 1) "正直的"
        else if (value >= 0) ""
        else if (value > -76) "卑鄙的"
        else "奸诈的"
    }
}
