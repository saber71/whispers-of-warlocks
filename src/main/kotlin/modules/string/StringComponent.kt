package heraclius.modules.string

import heraclius.core.Dict
import heraclius.core.ecs.EntityComponent

open class StringComponent(value: String) : EntityComponent<String>(value) {
    fun getValue(placeholderDict: Dict, wrappedSymbol: String = "$"): String {
        var string = getValue()
        for (entry in placeholderDict) {
            string = string.replace(wrappedSymbol + entry.key + wrappedSymbol, entry.value.toString())
        }
        return string
    }
}
