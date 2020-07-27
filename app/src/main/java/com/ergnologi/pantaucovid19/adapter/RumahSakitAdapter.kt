package com.ergnologi.pantaucovid19.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ergnologi.pantaucovid19.R
import com.ergnologi.pantaucovid19.models.RumahSakitModels

class RumahSakitAdapter(private val list: ArrayList<RumahSakitModels>) :
    RecyclerView.Adapter<RumahSakitAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_rumah_sakit, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (nama, id, alamat, geometry) = list[position]
        holder.txtNamaRS.text = nama
        holder.txtAlamat.text = alamat
        holder.id = id
        holder.lat = geometry.location.lat
        holder.long = geometry.location.lng
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtNamaRS: TextView = itemView.findViewById(R.id.txtNamaRS)
        var txtAlamat: TextView = itemView.findViewById(R.id.txtAlamatRumahSakit)
        var id: String = ""
        var lat: String = ""
        var long: String = ""
    }
}