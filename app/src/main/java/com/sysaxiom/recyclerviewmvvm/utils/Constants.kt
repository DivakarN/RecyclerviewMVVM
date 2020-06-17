package com.sysaxiom.recyclerviewmvvm.utils

object UrlsFields {

    const val BaseURL = "https://spotrush.in/api/v1/"
    const val ID = "id"
    const val LAT = "lat"
    const val LON = "lon"
    const val DATE = "date"
    const val TIME = "time"
    const val ADDRESS = "address"
    const val SPECIALITY = "speciality"
    const val NOTES = "notes"
    const val GET_APPOINTMENT = "patient/getAppointment"
    const val MAXIMUM_TIMEOUT = 90
    const val NO_INTERNET_CONNECTTION = "No Internet Connecttion"
    const val MQTT_DEV : String = "wss://spotrush.in:8084/mqtt"
    const val MQTT_DEV_USERNAME : String = "admin"
    const val MQTT_DEV_PASSWORD : String ="public"
    const val MQTT_DEV_CLIENT_ID : String ="web_android_spot"
}

fun returnMqttTopicNames(): ArrayList<String> {
    val mqttTopicName = ArrayList<String>()
    mqttTopicName.add("hello_world")
    return mqttTopicName
}
