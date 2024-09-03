package heraclius.modules.personality.ck3

//性格的数据
data class CK3PersonalityData(
    val englishName: String,
    val chineseName: String,
    val description: String,
    private val inner: String,
    val mutex: List<String>
) {
    fun getInner(): List<Pair<Int, String>> {
        return inner.split("\n").filter { it.isNotEmpty() }.map {
            val split = it.split(" ")
            Pair(split[0].replace('−', '-').toInt(), split[1])
        }
    }
}
