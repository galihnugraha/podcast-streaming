package com.proyekakhir.podcaststreaming.domain.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ReqPodcastDto(
        @field:NotBlank(message = "title cant be blank")
        val title:String?,

        @field:NotBlank(message = "author cant be blank")
        val author:String?,

        @field:NotNull(message = "duration cant be blank")
        val duration:Int?,

        @field:NotBlank(message = "idtype cant be blank")
        val idType: String?,

        @field:NotNull(message = "idgenre cant be blank")
        val idGenre: Int?,
)
