package com.sysaxiom.recyclerviewmvvm.handlers.mqtt

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.sysaxiom.recyclerviewmvvm.utils.UrlsFields.MQTT_DEV
import com.sysaxiom.recyclerviewmvvm.utils.UrlsFields.MQTT_DEV_CLIENT_ID
import com.sysaxiom.recyclerviewmvvm.utils.UrlsFields.MQTT_DEV_PASSWORD
import com.sysaxiom.recyclerviewmvvm.utils.UrlsFields.MQTT_DEV_USERNAME
import com.sysaxiom.recyclerviewmvvm.utils.returnMqttTopicNames
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*

class MqttHandler(private val context: Context) : LiveData<MqttData>() {

    var client: MqttAndroidClient? = null
    var options: MqttConnectOptions? = null

    override fun onActive() {
        super.onActive()
        try {
            val list = MqttHelper.mqttConnectionDetails(
                context,
                MQTT_DEV,
                MQTT_DEV_USERNAME,
                MQTT_DEV_PASSWORD,
                MQTT_DEV_CLIENT_ID
            )
            client = list.get(0).client
            options = list.get(0).options
            MqttHelper.connectToMqtt(client!!, options!!, returnMqttTopicNames())
        } catch (e: Exception) {
            Log.d("MQTTHandler", e.toString())
        }

        client?.setCallback(object : MqttCallback {
            override fun messageArrived(topic: String?, message: MqttMessage?) {
                postValue(MqttData(true,topic.toString(),message.toString()))
            }

            override fun connectionLost(cause: Throwable?) {
                postValue(MqttData(false,"",""))
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                //do nothing
            }
        })
    }

    override fun onInactive() {
        super.onInactive()
        try{
            client?.disconnect()
        } catch (e: Exception){
            println(e)
        }
    }

}

class MqttData(
    var isConnected : Boolean,
    var topic : String,
    var mqttMessage : String
)