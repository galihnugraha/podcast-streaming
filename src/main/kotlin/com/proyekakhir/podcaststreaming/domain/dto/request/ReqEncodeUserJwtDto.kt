package com.proyekakhir.podcaststreaming.domain.dto.request

data class ReqEncodeUserJwtDto(
        val idUser:String?,

        val username:String?,

        val email:String?,

        val type: String?,
)