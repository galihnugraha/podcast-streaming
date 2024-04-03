package com.proyekakhir.podcaststreaming.service

import com.proyekakhir.podcaststreaming.domain.dto.request.ReqGenreDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResMessageDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResGenreDto

interface GenreService {
    fun insert(req: ReqGenreDto): ResMessageDto<ResGenreDto>

    fun list(): ResMessageDto<List<ResGenreDto>>

    fun detail(id: Int): ResMessageDto<ResGenreDto>

    fun update(id: Int, req: ReqGenreDto): ResMessageDto<ResGenreDto>

    fun delete(id: Int): ResMessageDto<String>
}