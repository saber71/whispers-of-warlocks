package heraclius.paramters

import heraclius.core.Symbols

// 宝可梦参数
object PokemonParameters {
    // 性格的正面参数
    val PERSONALITY_POSITIVE_FACTOR = { Symbols.use("positive", 0.1).value<Number>() }

    // 性能的负面参数
    val PERSONALITY_NEGATIVE_FACTOR = { Symbols.use("positive", -0.1).value<Number>() }
}
