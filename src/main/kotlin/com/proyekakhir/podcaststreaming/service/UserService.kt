package com.proyekakhir.podcaststreaming.service

import com.proyekakhir.podcaststreaming.domain.dto.request.ReqUserDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResMessageDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResUserDto

interface UserService {
    fun insert(req: ReqUserDto): ResMessageDto<ResUserDto>

    fun list(): ResMessageDto<List<ResUserDto>>

    fun detail(id: Int): ResMessageDto<ResUserDto>

    fun update(id: Int, req: ReqUserDto): ResMessageDto<ResUserDto>

    fun delete(id: Int): ResMessageDto<String>
}