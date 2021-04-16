package com.example.continuada02

import com.google.gson.annotations.SerializedName

data class Cachorros (
    @SerializedName("id")
    var id : Int,

    @SerializedName("raca")
    var raca : String,

    @SerializedName("precoMedio")
    var precoMedio : String,

    @SerializedName("indicadoCriancas")
    var indicadoCriancas : Boolean

)