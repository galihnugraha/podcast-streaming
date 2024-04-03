package com.proyekakhir.podcaststreaming.domain.dto.response

import java.time.LocalDateTime

data class ResFavoriteDto(
        val idFavorite:Int?,

        val titlePodcast: String?,

        val userAdded:String?,

        val dtAdded: LocalDateTime?,

        val dtUpdated: LocalDateTime?,
)
