package com.ergnologi.pantaucovid19.models

data class RumahSakitModels(
    var name: String = "",
    var place_id: String = "",
    var vicinity: String = "",
    var geometry: GeometryModels
)