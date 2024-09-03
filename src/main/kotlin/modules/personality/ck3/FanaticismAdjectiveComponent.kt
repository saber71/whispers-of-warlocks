package heraclius.modules.personality.ck3

class FanaticismAdjectiveComponent(value: Int) : CK3PersonalityDescriptionComponent(value) {
    override fun describe(value: Int): String {
        return if (value >= 76) "热枕的"
        else if (value >= 1) "虔诚的"
        else if (value >= 0) ""
        else if (value > -76) "愤世嫉俗的"
        else "不敬神的"
    }
}
