package com.ergnologi.pantaucovid19.data

import com.ergnologi.pantaucovid19.R
import com.ergnologi.pantaucovid19.models.ArtikelModels

object ArtikelData {
    private val judul = arrayOf(
        "Cara Mencuci Tangan Dengan Baik dan Benar",
        "Apa Itu Covid19 ?"
    )

    private val gambar = intArrayOf(R.drawable.artikel1, R.drawable.artikel2)

    private val isi = arrayOf(
        "Organisasi Kesehatan Dunia (World Health Organization/WHO) menyarankan setiap orang untuk selalu mencuci tangan demi mencegah penyebaran virus corona COVID-19. Bersihkan tangan secara teratur dan menyeluruh dengan sabun dan air atau cairan berbasis alkohol.\n\n" +
                "Mencuci tangan dapat membuat Anda tetap sehat dan mencegah penyebaran infeksi pernapasan dan diare dari satu orang ke orang lain. Kuman dapat menyebar dari orang lain atau permukaan saat Anda:\n\n" +
                "1. Menyentuh mata, hidung, dan mulut Anda dengan tangan yang tidak dicuci. \n" +
                "2. Makan atau menyiapkan makanan dan minuman dengan tangan yang tidak dicuci. \n" +
                "3. Menyentuh permukaan atau benda yang terkontaminasi. \n" +
                "4. Mengesang, batuk, atau bersin ke tangan dan kemudian menyentuh tangan orang lain atau benda-benda.\n\n" +
                "Menurut WHO, mencuci tangan agar bersih menghabiskan waktu sekitar 20-30 detik. Ikuti 7 langkah mencuci tangan yang benar menurut WHO untuk mencegah infeksi virus, kuman, dan bakteri.\n\n" +
                "1. Basahi tangan dan tuangkan atau oleskan produk sabun di telapan tangan. " +
                "\n2. Tangkupkan kedua telapak tangan dan gosokkan produk sabun yang telah dituangkan. " +
                "\n3. Letakkan telapak tangan kanan di atas punggung tangan kiri dengan jari yang terjalin dan ulangi untuk sebaliknya. \n" +
                "4. Letakkan telapak tangan kanan ke telapak tangan kiri dengan jari saling terkait. \n" +
                "5. Tangan kanan dan kiri saling menggenggam dan jari bertautan agar sabun mengenai kuku dan pangkal jari. \n" +
                "6. Gosok ibu jari kiri dengan menggunakan tangan kanan dan sebaliknya. \n" +
                "7. Gosokkan jari-jari tangan kanan yang tergenggam di telapak tangan kiri dan sebaliknya. \n" +
                "8. Bilas dan keringkan. Setelah kering, tangan Anda sudah aman dari bakteri dan kotoran.\n\n" +
                "Untuk lebih jelasnya, silahkan simak video berikut.",
        "Infeksi virus Corona disebut COVID-19 (Corona Virus Disease 2019) dan pertama kali ditemukan di kota Wuhan, China pada akhir Desember 2019. Virus ini menular dengan sangat cepat dan telah menyebar ke hampir semua negara, termasuk Indonesia, hanya dalam waktu beberapa bulan.\n\n" +
                "Hal tersebut membuat beberapa negara menerapkan kebijakan untuk memberlakukan lockdown dalam rangka mencegah penyebaran virus Corona. Di Indonesia sendiri, diberlakukan kebijakan Pembatasan Sosial Berskala Besar (PSBB) untuk menekan penyebaran virus ini.\n\n" +
                "Coronavirus adalah kumpulan virus yang bisa menginfeksi sistem pernapasan. Pada banyak kasus, virus ini hanya menyebabkan infeksi pernapasan ringan, seperti flu. Namun, virus ini juga bisa menyebabkan infeksi pernapasan berat, seperti infeksi paru-paru (pneumonia).\n\n" +
                "Selain virus SARS-CoV-2 atau virus Corona, virus yang juga termasuk dalam kelompok ini adalah virus penyebab Severe Acute Respiratory Syndrome (SARS) dan virus penyebab Middle-East Respiratory Syndrome (MERS). Meski disebabkan oleh virus dari kelompok yang sama, yaitu coronavirus, COVID-19 memiliki beberapa perbedaan dengan SARS dan MERS, antara lain dalam hal kecepatan penyebaran dan keparahan gejala.\n\n" +
                "Virus Corona yang menyebabkan COVID-19 bisa menyerang siapa saja. Menurut data yang dirilis Gugus Tugas Percepatan Penanganan COVID-19 Republik Indonesia, jumlah kasus terkonfirmasi positif hingga 27 Juli 2020 adalah 98.778 orang dengan jumlah kematian 4.781 orang. Tingkat kematian (case fatality rate) akibat COVID-19 adalah sekitar 4,8%.\n\n" +
                "Gejala awal infeksi virus Corona atau COVID-19 bisa menyerupai gejala flu, yaitu demam, pilek, batuk kering, sakit tenggorokan, dan sakit kepala. Setelah itu, gejala dapat hilang dan sembuh atau malah memberat. Penderita dengan gejala yang berat bisa mengalami demam tinggi, batuk berdahak bahkan berdarah, sesak napas, dan nyeri dada. Gejala-gejala tersebut muncul ketika tubuh bereaksi melawan virus Corona.\n\n" +
                "Untuk penjelasan lebih lengkapnya, silahkan putar video dibawah ini."
    )

    private val video = arrayOf("HChg7g6oPjE", "OPeuzQYRzAM")

    private val penulis = arrayOf("Hai Dokter", "RS Akademi UGM")

    val listData: ArrayList<ArtikelModels>
        get() {
            val list = arrayListOf<ArtikelModels>()
            for (position in judul.indices) {
                val artikel = ArtikelModels()
                artikel.gambar = gambar[position]
                artikel.judul = judul[position]
                artikel.isi = isi[position]
                artikel.penulis = penulis[position]
                artikel.video = video[position]
                list.add(artikel)
            }
            return list
        }
}