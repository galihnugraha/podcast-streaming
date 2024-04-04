package com.proyekakhir.podcaststreaming.controller

import com.proyekakhir.podcaststreaming.domain.dto.request.ReqFavoriteDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResFavoriteDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResMessageDto
import com.proyekakhir.podcaststreaming.service.FavoriteService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/favorite")
class FavoriteController(
        val favoriteService: FavoriteService
) {
    @PostMapping
    fun insert(
            @RequestHeader("token") token:String,
            @Valid
            @RequestBody request: ReqFavoriteDto
    ): ResponseEntity<ResMessageDto<ResFavoriteDto>> {
        val response = favoriteService.insert(token, request)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/list")
    fun list(@RequestHeader("token") token:String): ResponseEntity<ResMessageDto<List<ResFavoriteDto>>> {
        val response = favoriteService.list(token)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/detail")
    fun detail(
            @RequestHeader("token") token:String,
            @RequestParam id: Int
    ): ResponseEntity<ResMessageDto<ResFavoriteDto>> {
        val response = favoriteService.detail(token, id)
        return ResponseEntity.ok(response)
    }

    @PutMapping
    fun update(
            @RequestHeader("token") token:String,
            @RequestParam id: Int,
            @Valid @RequestBody request: ReqFavoriteDto
    ): ResponseEntity<ResMessageDto<ResFavoriteDto>> {
        val response = favoriteService.update(token, id, request)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/delete")
    fun delete(
            @RequestHeader("token") token:String,
            @RequestParam id: Int
    ): ResponseEntity<ResMessageDto<String>> {
        val response = favoriteService.delete(token, id)
        return ResponseEntity.ok(response)
    }
}