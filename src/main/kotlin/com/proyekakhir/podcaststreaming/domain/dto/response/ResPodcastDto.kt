package com.proyekakhir.podcaststreaming.domain.dto.response

import java.time.LocalDateTime

data class ResPodcastDto(
        val idPodcast:Int?,

        val title:String?,

        val author:String?,

        val duration:Int?,

        val type: String?,

        val genre: String?,

        val dtAdded: LocalDateTime?,

        val dtUpdated: LocalDateTime?
)
