package com.proyekakhir.podcaststreaming.service

import com.proyekakhir.podcaststreaming.domain.dto.request.ReqFavoriteDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResMessageDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResFavoriteDto

interface FavoriteService {
    fun insert(token: String, req: ReqFavoriteDto): ResMessageDto<ResFavoriteDto>

    fun list(token: String): ResMessageDto<List<ResFavoriteDto>>

    fun detail(token: String, id: Int): ResMessageDto<ResFavoriteDto>

    fun update(token: String, id: Int, req: ReqFavoriteDto): ResMessageDto<ResFavoriteDto>

    fun delete(token: String, id: Int): ResMessageDto<String>
}