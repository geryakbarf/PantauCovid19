package com.ergnologi.pantaucovid19.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ergnologi.pantaucovid19.R
import com.ergnologi.pantaucovid19.models.ArtikelModels
import com.ergnologi.pantaucovid19.ui.DetailArtikelActivity

class ArtikelAdapter(private var list: ArrayList<ArtikelModels>) :
    RecyclerView.Adapter<ArtikelAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_artikel, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (judul, penulis, gambar, video, isi) = list[position]
        holder.txtPenulis.text = "Oleh : $penulis"
        holder.txtJudul.text = judul
        Glide.with(holder.itemView.context).load(gambar).into(holder.imgArtikel)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailArtikelActivity::class.java)
            intent.putExtra("judul", judul)
            intent.putExtra("penulis", penulis)
            intent.putExtra("gambar", gambar)
            intent.putExtra("video", video)
            intent.putExtra("isi", isi)
            holder.itemView.context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtJudul: TextView = itemView.findViewById(R.id.txtJudul)
        var txtPenulis: TextView = itemView.findViewById(R.id.txtPenulis)
        var imgArtikel: ImageView = itemView.findViewById(R.id.imgThumbnail)
    }
}