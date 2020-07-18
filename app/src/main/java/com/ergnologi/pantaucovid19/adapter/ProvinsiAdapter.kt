package com.ergnologi.pantaucovid19.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ergnologi.pantaucovid19.R
import com.ergnologi.pantaucovid19.models.ProvinsiModels

class ProvinsiAdapter(private val list: ArrayList<ProvinsiModels>) :
    RecyclerView.Adapter<ProvinsiAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_provinsi, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (nama, kasus, sembuh, meninggal) = list[position]
        holder.txtNamaProvinsi.text = nama
        holder.txtKasus.text = kasus
        holder.txtSembuh.text = sembuh
        holder.txtMeninggal.text = meninggal
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtNamaProvinsi: TextView = itemView.findViewById(R.id.txtProvinsi)
        var txtSembuh: TextView = itemView.findViewById(R.id.txtSembuh)
        var txtKasus: TextView = itemView.findViewById(R.id.txtTotalKasus)
        var txtMeninggal: TextView = itemView.findViewById(R.id.txtMeninggal)
    }

}