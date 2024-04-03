package com.proyekakhir.podcaststreaming.service

import com.proyekakhir.podcaststreaming.domain.dto.request.ReqTypeDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResTypeDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResMessageDto

interface TypeService {
    fun insert(req: ReqTypeDto): ResMessageDto<ResTypeDto>

    fun list(): ResMessageDto<List<ResTypeDto>>

    fun detail(id: String): ResMessageDto<ResTypeDto>

    fun update(id: String, req: ReqTypeDto): ResMessageDto<ResTypeDto>

    fun delete(id: String): ResMessageDto<String>
}