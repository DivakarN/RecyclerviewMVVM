package com.sysaxiom.recyclerviewmvvm.ui.appointment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sysaxiom.recyclerviewmvvm.data.models.AppointmentData
import com.sysaxiom.recyclerviewmvvm.R

class AppointmentAdapter(var appointmentList : List<AppointmentData?>) : RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.appointment_card_view, parent, false)
        return AppointmentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return appointmentList.size
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        var appointment : AppointmentData? = appointmentList[position]

        if(appointment?.hospital_name.isNullOrEmpty()){
            holder.text_view_hospital_name.setText("--")
        } else {
            holder.text_view_hospital_name.setText(appointment?.hospital_name)
        }

        if(appointment?.hospital_address.isNullOrEmpty()){
            holder.text_view_hospital_address.setText("--")
        } else {
            holder.text_view_hospital_address.setText(appointment?.hospital_address)
        }

        if(appointment?.specialization.isNullOrEmpty()){
            holder.text_view_specialization.setText("--")
        } else {
            holder.text_view_specialization.setText(appointment?.specialization)
        }

        if(appointment?.distance.isNullOrEmpty()){
            holder.text_view_distance.setText("--")
        } else {
            holder.text_view_distance.setText(appointment?.distance)
        }

    }

    inner class AppointmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text_view_hospital_name : TextView = itemView.findViewById(R.id.text_view_hospital_name)
        var text_view_hospital_address : TextView = itemView.findViewById(R.id.text_view_hospital_address)
        var text_view_specialization : TextView = itemView.findViewById(R.id.text_view_specialization)
        var text_view_distance : TextView = itemView.findViewById(R.id.text_view_distance)
    }
}
