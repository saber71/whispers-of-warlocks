package heraclius.modules.personality_ck3

import heraclius.core.ecs.Ecs
import heraclius.core.ecs.EntityInitializer
import heraclius.core.resources.ResourceLoader
import heraclius.modules.personality.MutexPersonalityComponent
import heraclius.modules.personality.PersonalityEntity
import heraclius.modules.string.ChineseNameComponent
import heraclius.modules.string.DescriptionComponent
import heraclius.modules.string.EnglishNameComponent

class CK3PersonalityEntityInitializer : EntityInitializer() {
    override fun init() {
        val factory = Ecs.entityFactory(PersonalityEntity::class.java)
        val personalityDataArray =
            ResourceLoader.load("\\personality\\ck3.json").fromJSON(Array<CK3PersonalityData>::class.java)
        for (data in personalityDataArray) {
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
