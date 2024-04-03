package com.proyekakhir.podcaststreaming.domain.dto.request

import com.proyekakhir.podcaststreaming.domain.entity.TypeEntity
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class ReqUserDto(
        @field:NotEmpty(message = "username cannot empty")
        @field:Size(
                max = 32,
                message = "username cannot more than 32 character"
        )
        @field:Pattern(
                regexp = "^[^\\s]*\$",
                message = "username cannot using space"
        )
        val username: String?,

        @field:NotBlank(message = "email cannot empty")
        @field:Pattern(
                regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$",
                message = "email invalid"
        )
        val email: String?,

        @field:NotBlank(message = "password cannot empty")
        @field:Size(
                max = 12,
                message = "password cannot more than 12 character"
        )
        val password: String?,

        val idType: String?
)
