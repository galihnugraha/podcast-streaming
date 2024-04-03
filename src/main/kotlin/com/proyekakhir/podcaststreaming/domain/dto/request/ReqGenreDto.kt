package com.proyekakhir.podcaststreaming.domain.dto.request

import jakarta.validation.constraints.NotBlank

data class ReqGenreDto(
        @field:NotBlank(message = "genre name cannot be blank")
        val genre:String?,
)
