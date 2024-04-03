package com.proyekakhir.podcaststreaming.controller

import com.proyekakhir.podcaststreaming.domain.dto.request.ReqUserDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResMessageDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResUserDto
import com.proyekakhir.podcaststreaming.service.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/user")
class UserController(
        val userService: UserService
) {
    @PostMapping
    fun insert(@Valid @RequestBody request: ReqUserDto): ResponseEntity<ResMessageDto<ResUserDto>> {
        val response = userService.insert(request)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/list")
    fun list(): ResponseEntity<ResMessageDto<List<ResUserDto>>> {
        val response = userService.list()
        return ResponseEntity.ok(response)
    }

    @GetMapping("/detail")
    fun detail(@RequestParam id: Int): ResponseEntity<ResMessageDto<ResUserDto>> {
        val response = userService.detail(id)
        return ResponseEntity.ok(response)
    }

    @PutMapping
    fun update(
            @RequestParam id: Int,
            @RequestBody request: ReqUserDto
    ): ResponseEntity<ResMessageDto<ResUserDto>> {
        val response = userService.update(id, request)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam id: Int): ResponseEntity<ResMessageDto<String>> {
        val response = userService.delete(id)
        return ResponseEntity.ok(response)
    }
}