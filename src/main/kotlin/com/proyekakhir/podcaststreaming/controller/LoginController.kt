package com.proyekakhir.podcaststreaming.controller

import com.proyekakhir.podcaststreaming.domain.dto.request.ReqLoginDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResEncodeJwtDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResMessageDto
import com.proyekakhir.podcaststreaming.service.LoginService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/api/login")
class LoginController(
        val loginService: LoginService
) {
    @PostMapping
    fun login(@Valid @RequestBody request:ReqLoginDto): ResponseEntity<ResMessageDto<ResEncodeJwtDto>> {
        val response = loginService.login(request)
        return ResponseEntity.ok(response)
    }
}