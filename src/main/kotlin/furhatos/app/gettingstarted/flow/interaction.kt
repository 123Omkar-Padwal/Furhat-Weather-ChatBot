package furhatos.app.gettingstarted.flow

import furhatos.app.gettingstarted.GetDetails
import furhatos.app.gettingstarted.GetWeather
import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.nlu.wikidata.City
import org.json.JSONObject
import java.time.LocalDate
import java.time.LocalTime
import java.time.Year
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import furhatos.app.gettingstarted.flow.Weather as Weather1

var query = String()
var main = JSONObject()
var wind = JSONObject()
var clouds = JSONObject()
var count = 0
var count1 = count
val Start : State = state(Interaction) {

    onEntry {
        count1 += 1
        if (count1==1){
        furhat.say("Hi there. I am furhat weather")}
        goto(Weather1)
    }

    onResponse<No>{
        furhat.say("That's sad.")
    }
}



val Weather = state {
    onEntry {
        furhat.ask("Which city's weather would you like to know?")
    }

    onResponse<GetWeather>{
        if (it.intent.city==null || it.intent.date==null){
            if (it.intent.city==null){
                it.intent.city = furhat.askFor<City>("Which city's weather do you want to know")
            }
           /* else{
                it.intent.date = furhat.askFor<Date>("Which date you want to look for?")
            }*/
        }
       // furhat.say("Okay ${it.intent.city} on ${it.intent.date}")
        if (it.intent.date!=null){
            val date = convert(it.intent.date.toString())
            val BASE_URL = "https://history.openweathermap.org/data/2.5/history/city?q="
            val APP_ID = "e74a0e76b219fdc73d7011c2190babfb"
            print(date)
            print("${it.intent.city}")
            query = "$BASE_URL${it.intent.city}&start=$date&APPID=$APP_ID&units=metric"
            val response = khttp.get(query).text
            val objJSON = JSONObject(response)
            val list = objJSON.getJSONArray("list")
            val main1 = list.getJSONObject(0)
            main = main1.getJSONObject("main")
            wind = main1.getJSONObject("wind")
            clouds = main1.getJSONObject("clouds")
        }
        else{
        val BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q="
        val APP_ID = "6fb51bcbe9dac270c88f884296406c12"
        query = "$BASE_URL${it.intent.city}&APPID=$APP_ID&units=metric"
            val response = khttp.get(query).text
            val objJSON = JSONObject(response)
            main = objJSON.getJSONObject("main")
            wind = objJSON.getJSONObject("wind")
            clouds = objJSON.getJSONObject("clouds")
        }
        //print(query)
        //val response = khttp.get(query).text
        //print(response)
        //furhat.say("$response")
       // val objJSON = JSONObject(response)
        //val list = objJSON.getJSONArray("list")
        //val main1 = list.getJSONObject(0)
        //val main = main1.getJSONObject("main")
        print(main)
        val temp = main.getDouble("temp")
        val temp_max = main.getDouble("temp_max")
        val temp_min = main.getDouble("temp_min")
        val cloud = clouds.getDouble("all")
        furhat.say("Temperature is $temp degree celsius")
        furhat.say("Maximum temperature is $temp_max degree celsius")
        furhat.say("Minimum temperature is $temp_min degree celsius")
        furhat.say("Cloudiness is $cloud percent")
        goto(state = Details(main,wind))
    }
}

fun Details(main: JSONObject, wind: JSONObject)=state{
    onEntry {
        furhat.ask("Would you like to know anything else")
    }
    onResponse<GetDetails> {
        //val main = objJSON.getJSONObject("main")
        val humidity = main.getDouble("humidity")
        val pressure = main.getDouble("pressure")
        val speed = wind.getDouble("speed")
        if ("${it.intent.detail}"=="humidity" || "${it.intent.detail}"=="humid"){
            furhat.say("Humidity is $humidity percent")
        }
        else if ("${it.intent.detail}"=="windy"){
            furhat.say("Humidity is $speed metres/sec")
        }
        else{
            furhat.say("Pressure is $pressure hecto pascals")
        }
        furhat.ask("Would you like to know temperature of any other city")
    }

    onResponse<Yes> {
        goto(Start)
    }
    onResponse<No>{
        furhat.say("Goodbye")
    }
}

fun convert(str: String): Long {
    //val str = "23rd november 2015"
    val date = "$str 2022"
    val str1 = date.replace("th","").replace("nd","").replace("st","").replace("rd","").replace("on","").replace("the","").replace("of","").replace("for","")
    var str2 = String()
    if ("January" in str1){
        str2=str1.replace("January","01")
    }
    else if ("February" in str1){
        str2=str1.replace("February","02")
    }
    else if ("March" in str1){
        str2=str1.replace("March","03")
    }
    else if ("April" in str1){
        str2=str1.replace("April","04")
    }
    else if ("May" in str1){
        str2=str1.replace("May","05")
    }
    else if ("June" in str1){
        str2=str1.replace("June","06")
    }
    else if ("July" in str1){
        str2=str1.replace("July","07")
    }
    else if ("August" in str1){
        str2=str1.replace("August","08")
    }
    else if ("September" in str1){
        str2=str1.replace("September","09")
    }
    else if ("October" in str1){
        str2=str1.replace("October","10")
    }
    else if ("November" in str1){
        str2=str1.replace("November","11")
    }
    else if ("December" in str1){
        str2=str1.replace("December","12")
    }
    str2 = str2.trimStart()
    str2 = str2.replace(" ","-")
    //print(str2)
    //val date = SimpleDateFormat("dd-mm-yyyy").parse(str2)
    //println(date.time)
    val l = LocalDate.parse(str2, DateTimeFormatter.ofPattern("d-MM-yyyy")).atTime(LocalTime.now().minusMinutes(60)).atZone(ZoneId.systemDefault())

    val unix = l.toInstant().epochSecond
    return unix
}


