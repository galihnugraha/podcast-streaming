package com.proyekakhir.podcaststreaming.domain.dto.request

import jakarta.validation.constraints.NotBlank

data class ReqLoginDto(
        @field:NotBlank(message = "Username cannot empty")
        val username:String = "",

        @field:NotBlank(message = "Password cannot empty")
        val password:String = "",
)
