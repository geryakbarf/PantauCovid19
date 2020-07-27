package com.ergnologi.pantaucovid19.data

import com.ergnologi.pantaucovid19.models.PertanyaanModels

object PertanyaanData {
    private val pertanyaan = arrayOf(
        "Apakah anda seorang perokok yang memiliki gangguan pada paru - paru ?",
        "Apakah anda memiliki penyakit kronis seperti kanker, gagal ginjal, dan gangguan pada jantung ?",
        "Dalam kurun 14 hari, apakah anda pernah kontak langsung dengan pengidap Covid19 ?",
        "Dalam kurun 14 hari, apakah anda pernah berpergian ke daerah zona merah keatas ?",
        "Dalam kurun 14 hari, apakah anda mengalami masalah demam ?",
        "Dalam kurun 14 hari, apakah anda mengalami pilek/cairan yang keluar dari hidung anda ?",
        "Dalam kurun 14 hari, apakah anda mengalami batuk tampak bercampur dengan darah ?",
        "Dalam kurun 14 hari, apakah anda sering bersin - bersin ?",
        "Dalam kurun 14 hari, apakah anda sering merasa lelah dengan cepat ?",
        "Dalam kurun 14 hari, apakah anda sering mengalami sakit kepala ?"
    )

    val listData: ArrayList<PertanyaanModels>
        get() {
            val list = arrayListOf<PertanyaanModels>()
            for (position in pertanyaan.indices) {
                val pertanyaanModels = PertanyaanModels()
                pertanyaanModels.pertanyaan = pertanyaan[position]
                list.add(pertanyaanModels)
            }
            return list
        }
}