package com.proyekakhir.podcaststreaming.domain.dto.request

import jakarta.validation.constraints.NotNull

data class ReqFavoriteDto(
        @field:NotNull(message = "idpodcast cannot be blank")
        val idPodcast: Int?,
)
