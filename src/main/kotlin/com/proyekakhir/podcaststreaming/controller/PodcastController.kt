package com.proyekakhir.podcaststreaming.controller

import com.proyekakhir.podcaststreaming.domain.dto.request.ReqPodcastDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResMessageDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResPodcastDto
import com.proyekakhir.podcaststreaming.service.PodcastService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/podcast")
class PodcastController(
        val podcastService: PodcastService
) {
    @PostMapping
    fun insert(@Valid @RequestBody request: ReqPodcastDto): ResponseEntity<ResMessageDto<ResPodcastDto>> {
        val response = podcastService.insert(request)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/list")
    fun list(@RequestHeader("token") token:String): ResponseEntity<ResMessageDto<List<ResPodcastDto>>> {
        val response = podcastService.list(token)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/detail")
    fun detail(
            @RequestHeader("token") token:String,
            @RequestParam id: Int
    ): ResponseEntity<ResMessageDto<ResPodcastDto>> {
        val response = podcastService.detail(token, id)
        return ResponseEntity.ok(response)
    }

    @PutMapping
    fun update(
            @RequestParam id: Int,
            @Valid @RequestBody request: ReqPodcastDto
    ): ResponseEntity<ResMessageDto<ResPodcastDto>> {
        val response = podcastService.update(id, request)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam id: Int): ResponseEntity<ResMessageDto<String>> {
        val response = podcastService.delete(id)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/search")
    fun search(
            @RequestHeader("token") token:String,
            @RequestParam s: String
    ): ResponseEntity<ResMessageDto<List<ResPodcastDto>>> {
        val response = podcastService.search(token, s)
        return ResponseEntity.ok(response)
    }
}