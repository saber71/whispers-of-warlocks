package heraclius.modules.personality.ck3

class GreedAdjectiveComponent(value: Int) : CK3PersonalityDescriptionComponent(value) {
    override fun describe(value: Int): String {
        return if (value >= 76) "贪得无厌的"
        else if (value >= 1) "贪婪的"
        else if (value >= 0) ""
        else if (value > -76) "安于现状的"
        else "不思进取的"
    }
}
