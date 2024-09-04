package heraclius.paramters

import heraclius.core.Symbols

// 宝可梦参数
object PokemonParameters {
    // 性格的正面参数
    val PERSONALITY_POSITIVE_FACTOR = lazy { Symbols.use("PERSONALITY_POSITIVE_FACTOR", 0.1).value<Number>() }.value

    // 性格的负面参数
    val PERSONALITY_NEGATIVE_FACTOR = lazy { Symbols.use("PERSONALITY_NEGATIVE_FACTOR", -0.1).value<Number>() }.value
}
