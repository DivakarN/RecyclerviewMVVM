package com.sysaxiom.recyclerviewmvvm.ui.appointment

interface AppointmentListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}