package heraclius.modules.personality.pokemon

import heraclius.core.ecs.Ecs
import heraclius.core.ecs.EntityComponent
import heraclius.core.ecs.EntityInitializer
import heraclius.core.resources.ResourceLoader
import heraclius.core.value.Value
import heraclius.modules.personality.personality_pokemon.*
import heraclius.modules.string.ChineseNameComponent
import heraclius.modules.string.EnglishNameComponent
import heraclius.paramters.PokemonParameters
import java.nio.file.Paths

/**
 * 精灵宝可梦性格实体初始化器
 * 该类负责从JSON文件加载宝可梦性格数据，并初始化相应的实体
 */
class PokemonPersonalityEntityInitializer : EntityInitializer() {
    /**
     * 初始化精灵宝可梦性格实体
     * 从指定的JSON文件加载性格数据，并为每个性格创建实体
     */
    override fun init() {
        // 创建精灵宝可梦性格实体的工厂
        val factory = Ecs.entityFactory(PokemonPersonalityEntity::class.java)
        // 从资源文件加载宝可梦性格数据
        val array = ResourceLoader.load(Paths.get("/personality/pokemon.json"))
            .fromJSON(Array<PokemonPersonalityData>::class.java)
        // 遍历每个性格数据，创建对应的实体
        for (data in array) {
            // 创建实体组件列表，初始包含英文名和中文名组件
            val list = mutableListOf<EntityComponent<*>>(
                EnglishNameComponent(data.englishName),
                ChineseNameComponent(data.chineseName)
            )
            // 根据性格的正面属性设置内在因素组件
            if (data.positive.isNotEmpty()) {
                setupInnerFactor(list, data.positive, PokemonParameters.PERSONALITY_POSITIVE_FACTOR)
            }
            // 根据性格的负面属性设置内在因素组件
            if (data.negative.isNotEmpty()) {
                setupInnerFactor(list, data.negative, PokemonParameters.PERSONALITY_NEGATIVE_FACTOR)
            }
            // 如果性格有偏好口味，添加相应组件
            if (data.preferredTaste.isNotEmpty()) {
                list.add(PokemonTasteComponent(PokemonTaste.valueOf(data.preferredTaste)))
            }
            // 如果性格有不喜欢的口味，添加相应组件
            if (data.dislikeTaste.isNotEmpty()) {
                list.add(PokemonTasteComponent(PokemonTaste.valueOf(data.dislikeTaste)))
            }
            // 使用组件列表创建实体
            factory.new(*list.toTypedArray())
        }
    }

    /**
     * 设置内在因素组件
     * 根据属性名称和数值添加对应的内在因素组件到实体组件列表中
     *
     * @param list 实体组件列表
     * @param factor 属性名称
     * @param value 属性数值
     */
    private fun setupInnerFactor(list: MutableList<EntityComponent<*>>, factor: String, value: Value<Number>) {
        when (factor) {
            "Attack" -> list.add(InnerAttackFactor(value))
            "Defence" -> list.add(InnerDefenseFactor(value))
            "SpecialAttack" -> list.add(InnerSpecialAttackFactor(value))
            "SpecialDefence" -> list.add(InnerSpecialDefenseFactor(value))
            "Speed" -> list.add(InnerSpeedFactor(value))
            "HP" -> list.add(InnerHPFactor(value))
        }
    }
}
