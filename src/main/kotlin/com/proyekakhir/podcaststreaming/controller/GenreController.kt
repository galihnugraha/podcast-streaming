package com.proyekakhir.podcaststreaming.controller

import com.proyekakhir.podcaststreaming.domain.dto.request.ReqGenreDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResGenreDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResMessageDto
import com.proyekakhir.podcaststreaming.service.GenreService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/genre")
class GenreController(
        val genreService: GenreService
) {
    @PostMapping
    fun insert(@Valid @RequestBody request: ReqGenreDto): ResponseEntity<ResMessageDto<ResGenreDto>> {
        val response = genreService.insert(request)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/list")
    fun list(): ResponseEntity<ResMessageDto<List<ResGenreDto>>> {
        val response = genreService.list()
        return ResponseEntity.ok(response)
    }

    @GetMapping("/detail")
    fun detail(@RequestParam id: Int): ResponseEntity<ResMessageDto<ResGenreDto>> {
        val response = genreService.detail(id)
        return ResponseEntity.ok(response)
    }

    @PutMapping
    fun update(
            @RequestParam id: Int,
            @Valid @RequestBody request: ReqGenreDto
    ): ResponseEntity<ResMessageDto<ResGenreDto>> {
        val response = genreService.update(id, request)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam id: Int): ResponseEntity<ResMessageDto<String>> {
        val response = genreService.delete(id)
        return ResponseEntity.ok(response)
    }
}