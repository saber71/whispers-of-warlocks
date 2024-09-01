package heraclius.modules.personality_ck3

import heraclius.core.ecs.Ecs
import heraclius.core.ecs.EntityInitializer
import heraclius.modules.personality.MutexPersonalityComponent
import heraclius.modules.string.ChineseNameComponent
import heraclius.modules.string.EnglishNameComponent

class CK3PersonalityEntityInitializer : EntityInitializer() {
    override fun init() {
        Ecs.entityFactory(CK3PersonalityEntity::class.java)
            .new(
                EnglishNameComponent("brave"),
                ChineseNameComponent("勇敢"),
                CourageComponent(200f),
                VitalityComponent(20f),
                RationalComponent(-20f),
                SocialComponent(20f),
                MutexPersonalityComponent("craven")
            ).new(
                EnglishNameComponent("craven"),
                ChineseNameComponent("怯懦"),
                CourageComponent(-200f),
                VitalityComponent(-20f),
                RationalComponent(10f),
                SocialComponent(-20f),
                MutexPersonalityComponent("brave")
            ).new(
                EnglishNameComponent("calm"),
                ChineseNameComponent("冷静"),
                CourageComponent(-20f),
                VitalityComponent(-10f),
                RationalComponent(75f),
                VengeanceComponent(-10f),
                MutexPersonalityComponent("wrathful")
            ).new(
                EnglishNameComponent("wrathful"),
                ChineseNameComponent("暴怒"),
                CourageComponent(35f),
                VitalityComponent(10f),
                RationalComponent(-35f),
                VengeanceComponent(20f),
                PityComponent(10f),
                MutexPersonalityComponent("calm")
            ).new(
                EnglishNameComponent(""),
                ChineseNameComponent(""),
                CourageComponent(0f),// 胆量
                VitalityComponent(0f),// 精力
                RationalComponent(0f),// 理性
                VengeanceComponent(0f),// 报复
                PityComponent(0f),// 怜悯
                SocialComponent(0f),// 社交
                FanaticismComponent(0f),// 狂热
                GreedComponent(0f),// 贪婪
                HonorComponent(0f),// 荣誉
                MutexPersonalityComponent("")
            ).new(
                EnglishNameComponent(""),
                ChineseNameComponent(""),
                CourageComponent(0f),// 胆量
                VitalityComponent(0f),// 精力
                RationalComponent(0f),// 理性
                VengeanceComponent(0f),// 报复
                PityComponent(0f),// 怜悯
                SocialComponent(0f),// 社交
                FanaticismComponent(0f),// 狂热
                GreedComponent(0f),// 贪婪
                HonorComponent(0f),// 荣誉
                MutexPersonalityComponent("")
            ).new(
                EnglishNameComponent(""),
                ChineseNameComponent(""),
                CourageComponent(0f),// 胆量
                VitalityComponent(0f),// 精力
                RationalComponent(0f),// 理性
                VengeanceComponent(0f),// 报复
                PityComponent(0f),// 怜悯
                SocialComponent(0f),// 社交
                FanaticismComponent(0f),// 狂热
                GreedComponent(0f),// 贪婪
                HonorComponent(0f),// 荣誉
                MutexPersonalityComponent("")
            ).new(
                EnglishNameComponent(""),
                ChineseNameComponent(""),
                CourageComponent(0f),// 胆量
                VitalityComponent(0f),// 精力
                RationalComponent(0f),// 理性
                VengeanceComponent(0f),// 报复
                PityComponent(0f),// 怜悯
                SocialComponent(0f),// 社交
                FanaticismComponent(0f),// 狂热
                GreedComponent(0f),// 贪婪
                HonorComponent(0f),// 荣誉
                MutexPersonalityComponent("")
            ).new(
                EnglishNameComponent(""),
                ChineseNameComponent(""),
                CourageComponent(0f),// 胆量
                VitalityComponent(0f),// 精力
                RationalComponent(0f),// 理性
                VengeanceComponent(0f),// 报复
                PityComponent(0f),// 怜悯
                SocialComponent(0f),// 社交
                FanaticismComponent(0f),// 狂热
                GreedComponent(0f),// 贪婪
                HonorComponent(0f),// 荣誉
                MutexPersonalityComponent("")
            ).new(
                EnglishNameComponent(""),
                ChineseNameComponent(""),
                CourageComponent(0f),// 胆量
                VitalityComponent(0f),// 精力
                RationalComponent(0f),// 理性
                VengeanceComponent(0f),// 报复
                PityComponent(0f),// 怜悯
                SocialComponent(0f),// 社交
                FanaticismComponent(0f),// 狂热
                GreedComponent(0f),// 贪婪
                HonorComponent(0f),// 荣誉
                MutexPersonalityComponent("")
            ).new(
                EnglishNameComponent(""),
                ChineseNameComponent(""),
                CourageComponent(0f),// 胆量
                VitalityComponent(0f),// 精力
                RationalComponent(0f),// 理性
                VengeanceComponent(0f),// 报复
                PityComponent(0f),// 怜悯
                SocialComponent(0f),// 社交
                FanaticismComponent(0f),// 狂热
                GreedComponent(0f),// 贪婪
                HonorComponent(0f),// 荣誉
                MutexPersonalityComponent("")
            ).new(
                EnglishNameComponent(""),
                ChineseNameComponent(""),
                CourageComponent(0f),// 胆量
                VitalityComponent(0f),// 精力
                RationalComponent(0f),// 理性
                VengeanceComponent(0f),// 报复
                PityComponent(0f),// 怜悯
                SocialComponent(0f),// 社交
                FanaticismComponent(0f),// 狂热
                GreedComponent(0f),// 贪婪
                HonorComponent(0f),// 荣誉
                MutexPersonalityComponent("")
            ).new(
                EnglishNameComponent(""),
                ChineseNameComponent(""),
                CourageComponent(0f),// 胆量
                VitalityComponent(0f),// 精力
                RationalComponent(0f),// 理性
                VengeanceComponent(0f),// 报复
                PityComponent(0f),// 怜悯
                SocialComponent(0f),// 社交
                FanaticismComponent(0f),// 狂热
                GreedComponent(0f),// 贪婪
                HonorComponent(0f),// 荣誉
                MutexPersonalityComponent("")
            ).new(
                EnglishNameComponent(""),
                ChineseNameComponent(""),
                CourageComponent(0f),// 胆量
                VitalityComponent(0f),// 精力
                RationalComponent(0f),// 理性
                VengeanceComponent(0f),// 报复
                PityComponent(0f),// 怜悯
                SocialComponent(0f),// 社交
                FanaticismComponent(0f),// 狂热
                GreedComponent(0f),// 贪婪
                HonorComponent(0f),// 荣誉
                MutexPersonalityComponent("")
            ).new(
                EnglishNameComponent(""),
                ChineseNameComponent(""),
                CourageComponent(0f),// 胆量
                VitalityComponent(0f),// 精力
                RationalComponent(0f),// 理性
                VengeanceComponent(0f),// 报复
                PityComponent(0f),// 怜悯
                SocialComponent(0f),// 社交
                FanaticismComponent(0f),// 狂热
                GreedComponent(0f),// 贪婪
                HonorComponent(0f),// 荣誉
                MutexPersonalityComponent("")
            ).new(
                EnglishNameComponent(""),
                ChineseNameComponent(""),
                CourageComponent(0f),// 胆量
                VitalityComponent(0f),// 精力
                RationalComponent(0f),// 理性
                VengeanceComponent(0f),// 报复
                PityComponent(0f),// 怜悯
                SocialComponent(0f),// 社交
                FanaticismComponent(0f),// 狂热
                GreedComponent(0f),// 贪婪
                HonorComponent(0f),// 荣誉
                MutexPersonalityComponent("")
            ).new(
                EnglishNameComponent(""),
                ChineseNameComponent(""),
                CourageComponent(0f),// 胆量
                VitalityComponent(0f),// 精力
                RationalComponent(0f),// 理性
                VengeanceComponent(0f),// 报复
                PityComponent(0f),// 怜悯
                SocialComponent(0f),// 社交
                FanaticismComponent(0f),// 狂热
                GreedComponent(0f),// 贪婪
                HonorComponent(0f),// 荣誉
                MutexPersonalityComponent("")
            ).new(
                EnglishNameComponent(""),
                ChineseNameComponent(""),
                CourageComponent(0f),// 胆量
                VitalityComponent(0f),// 精力
                RationalComponent(0f),// 理性
                VengeanceComponent(0f),// 报复
                PityComponent(0f),// 怜悯
                SocialComponent(0f),// 社交
                FanaticismComponent(0f),// 狂热
                GreedComponent(0f),// 贪婪
                HonorComponent(0f),// 荣誉
                MutexPersonalityComponent("")
            ).new(
                EnglishNameComponent(""),
                ChineseNameComponent(""),
                CourageComponent(0f),// 胆量
                VitalityComponent(0f),// 精力
                RationalComponent(0f),// 理性
                VengeanceComponent(0f),// 报复
                PityComponent(0f),// 怜悯
                SocialComponent(0f),// 社交
                FanaticismComponent(0f),// 狂热
                GreedComponent(0f),// 贪婪
                HonorComponent(0f),// 荣誉
                MutexPersonalityComponent("")
            ).new(
                EnglishNameComponent(""),
                ChineseNameComponent(""),
                CourageComponent(0f),// 胆量
                VitalityComponent(0f),// 精力
                RationalComponent(0f),// 理性
                VengeanceComponent(0f),// 报复
                PityComponent(0f),// 怜悯
                SocialComponent(0f),// 社交
                FanaticismComponent(0f),// 狂热
                GreedComponent(0f),// 贪婪
                HonorComponent(0f),// 荣誉
                MutexPersonalityComponent("")
            ).new(
                EnglishNameComponent(""),
                ChineseNameComponent(""),
                CourageComponent(0f),// 胆量
                VitalityComponent(0f),// 精力
                RationalComponent(0f),// 理性
                VengeanceComponent(0f),// 报复
                PityComponent(0f),// 怜悯
                SocialComponent(0f),// 社交
                FanaticismComponent(0f),// 狂热
                GreedComponent(0f),// 贪婪
                HonorComponent(0f),// 荣誉
                MutexPersonalityComponent("")
            ).new(
                EnglishNameComponent(""),
                ChineseNameComponent(""),
                CourageComponent(0f),// 胆量
                VitalityComponent(0f),// 精力
                RationalComponent(0f),// 理性
                VengeanceComponent(0f),// 报复
                PityComponent(0f),// 怜悯
                SocialComponent(0f),// 社交
                FanaticismComponent(0f),// 狂热
                GreedComponent(0f),// 贪婪
                HonorComponent(0f),// 荣誉
                MutexPersonalityComponent("")
            ).new(
                EnglishNameComponent(""),
                ChineseNameComponent(""),
                CourageComponent(0f),// 胆量
                VitalityComponent(0f),// 精力
                RationalComponent(0f),// 理性
                VengeanceComponent(0f),// 报复
                PityComponent(0f),// 怜悯
                SocialComponent(0f),// 社交
                FanaticismComponent(0f),// 狂热
                GreedComponent(0f),// 贪婪
                HonorComponent(0f),// 荣誉
                MutexPersonalityComponent("")
            ).new(
                EnglishNameComponent(""),
                ChineseNameComponent(""),
                CourageComponent(0f),// 胆量
                VitalityComponent(0f),// 精力
                RationalComponent(0f),// 理性
                VengeanceComponent(0f),// 报复
                PityComponent(0f),// 怜悯
                SocialComponent(0f),// 社交
                FanaticismComponent(0f),// 狂热
                GreedComponent(0f),// 贪婪
                HonorComponent(0f),// 荣誉
                MutexPersonalityComponent("")
            )
    }
}
