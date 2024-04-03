package com.proyekakhir.podcaststreaming.domain.dto.request

import jakarta.validation.constraints.NotBlank

data class ReqTypeDto(
        val idType:String?,

        @field:NotBlank(message = "type name cannot be blank")
        val type:String?,
)
