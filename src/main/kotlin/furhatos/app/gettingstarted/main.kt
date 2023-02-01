package furhatos.app.gettingstarted

import furhatos.app.gettingstarted.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*
import furhatos.nlu.EnumEntity
import furhatos.nlu.Intent
import furhatos.nlu.common.Date
import furhatos.nlu.wikidata.City
import furhatos.util.Language

class GettingstartedSkill : Skill() {
    override fun start() {
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}

class GetWeather(var city: City?=null, var date: Date?=null): Intent(){
    override fun getExamples(lang: Language): List<String>? {
        return listOf("I want to check the weather for @date","I want to check in @city","Please check the weather for me in @city on @date","What does the weather on @date look like","Search in @city","Do you know the weather on @date","In @city, please","Please tell me the weather forecast for @date","Please check the weather in @city","What will the weather be like on @date in @city?","Can you find me the weather in @city for @date","Can you help me check the weather for @date for @city","I need the weather forecast for @date","Can you check the weather in @city for me","I will go to @city soon. Can you check the weather in there on @date","I would like to check the weather","What about @city on the @date","i want to check the weather in @city on the @date","What will the weather be like in @city on the @date","I would like to check the weather in @city.","Can you tell me what the weather is suppose to be like on @date in @city")
    }
}

class Details: EnumEntity(){
    override fun getEnum(lang: Language): List<String> {
        return listOf("humid","pressure","windy")
    }
}

class GetDetails(val detail: Details?=null): Intent(){
    override fun getExamples(lang: Language): List<String> {
        return listOf("@detail","how @detail would it be","how much @detail would be there","What will the @detail be","how @detail will it be")
    }
}
