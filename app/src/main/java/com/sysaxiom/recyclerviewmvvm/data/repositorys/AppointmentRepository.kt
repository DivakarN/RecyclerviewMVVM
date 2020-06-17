package com.sysaxiom.recyclerviewmvvm.data.repositorys

import com.sysaxiom.recyclerviewmvvm.data.models.AppointmentResponse
import com.sysaxiom.recyclerviewmvvm.data.network.NetworkApis
import com.sysaxiom.recyclerviewmvvm.data.network.SafeApiRequest

class AppointmentRepository (val networkApis: NetworkApis) : SafeApiRequest() {

    suspend fun getAppointment(id : String): AppointmentResponse {
        return apiRequest { networkApis.getAppointment(id) }
    }

}