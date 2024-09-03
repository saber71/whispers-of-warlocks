package heraclius.modules.personality.ck3

import heraclius.modules.string.DescriptionComponent

/**
 * 抽象类 CK3PersonalityDescriptionComponent 提供了对 CK3 性格描述组件的基本功能。
 * 它实现了 DescriptionComponent 接口，主要功能是正确格式化一个给定的数值，
 * 该数值通常代表某个性格特质的具体评分或程度。
 *
 * @param value Float 类型的值，代表需要被描述的特质评分。
 */
abstract class CK3PersonalityDescriptionComponent(private val value: Int) : DescriptionComponent("") {
    override fun value(): String {
        return describe(value)
    }

    /**
     * 抽象函数，用于具体实现对一个给定浮点数值的描述。
     * 子类需要实现这个函数来提供具体的描述逻辑。
     *
     * @param value 需要被描述的浮点数值。
     * @return 描述该数值的字符串。
     */
    protected abstract fun describe(value: Int): String
}
