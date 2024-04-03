package com.proyekakhir.podcaststreaming.controller

import com.proyekakhir.podcaststreaming.domain.dto.request.ReqTypeDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResMessageDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResTypeDto
import com.proyekakhir.podcaststreaming.service.TypeService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/type")
class TypeController(
        val typeService: TypeService
) {
    @PostMapping
    fun insert(@Valid @RequestBody request: ReqTypeDto): ResponseEntity<ResMessageDto<ResTypeDto>> {
        val response = typeService.insert(request)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/list")
    fun list(): ResponseEntity<ResMessageDto<List<ResTypeDto>>> {
        val response = typeService.list()
        return ResponseEntity.ok(response)
    }

    @GetMapping("/detail")
    fun detail(@RequestParam id: String): ResponseEntity<ResMessageDto<ResTypeDto>> {
        val response = typeService.detail(id)
        return ResponseEntity.ok(response)
    }

    @PutMapping
    fun update(
            @RequestParam id: String,
            @RequestBody request: ReqTypeDto
    ): ResponseEntity<ResMessageDto<ResTypeDto>> {
        val response = typeService.update(id, request)
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/delete")
    fun delete(@RequestParam id: String): ResponseEntity<ResMessageDto<String>> {
        val response = typeService.delete(id)
        return ResponseEntity.ok(response)
    }
}