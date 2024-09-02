package heraclius.modules.personality_pokemon

data class PokemonPersonalityData(
    val englishName: String,
    val chineseName: String,
    val positive: String,
    val negative: String,
    val preferredTaste: String,
    val dislikeTaste: String,
) {
}
