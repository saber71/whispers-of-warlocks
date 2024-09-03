package heraclius.modules.personality.ck3

import heraclius.core.ecs.Ecs
import heraclius.core.ecs.EntityInitializer
import heraclius.core.resources.ResourceLoader
import heraclius.modules.personality.MutexPersonalityComponent
import heraclius.modules.personality.personality_ck3.*
import heraclius.modules.string.ChineseNameComponent
import heraclius.modules.string.DescriptionComponent
import heraclius.modules.string.EnglishNameComponent
import java.nio.file.Paths

/**
 * 初始化CK3个性实体的类。
 * 该类负责从JSON资源文件中加载个性数据，并为每个个性数据创建实体。
 */
class CK3PersonalityEntityInitializer : EntityInitializer() {
    /**
     * 初始化实体。
     * 该方法首先创建一个实体工厂，然后加载个性数据的JSON文件，
     * 遍历数据数组，并为每个个性数据创建相应的实体。
     */
    override fun init() {
        // 创建CK3个性实体的工厂
        val factory = Ecs.entityFactory(CK3PersonalityEntity::class.java)
        // 加载并解析JSON资源文件中的个性数据
        val personalityDataArray =
            ResourceLoader.load(Paths.get("/personality/ck3.json")).fromJSON(Array<CK3PersonalityData>::class.java)

        // 遍历个性数据数组，为每个数据创建实体
        for (data in personalityDataArray) {
            // 提取并转换个性数据中的组件数据
            val components = data.getInner()
                .mapNotNull { (value, name) ->
                    when (name) {
                        "报复" -> VengeanceComponent(value)
                        "怜悯" -> PityComponent(value)
                        "精力" -> VitalityComponent(value)
                        "胆量" -> CourageComponent(value)
                        "社交" -> SocialComponent(value)
                        "理性" -> RationalComponent(value)
                        "贪婪" -> GreedComponent(value)
                        "狂热" -> FanaticismComponent(value)
                        "荣誉" -> HonorComponent(value)
                        else -> null
                    }
                }

            // 使用工厂方法创建实体，并设置各种组件
            factory.new(
                EnglishNameComponent(data.englishName),
                ChineseNameComponent(data.chineseName),
                DescriptionComponent(data.description),
                MutexPersonalityComponent(data.mutex),
                *components.toTypedArray()
            )
        }
    }
}
