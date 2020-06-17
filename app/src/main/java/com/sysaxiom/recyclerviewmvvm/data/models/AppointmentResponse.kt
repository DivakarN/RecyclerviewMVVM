package com.sysaxiom.recyclerviewmvvm.data.models

data class AppointmentResponse (
    val success: Boolean,
    val status: Int,
    val message: String,
    val data: List<AppointmentData>
)

data class AppointmentData (
    val _id: String,
    val updated_at: String,
    val created_at: String,
    val date: String,
    val booking_status: String,
    val hospital_name: String,
    val hospital_address: String,
    val specialization: String,
    val distance: String,
    val notes: String,
    val lat: String,
    val lon: String,
    val address: String,
    val user_id: String,
    val time: String,
    val status: String,
    val __v: Long
)

