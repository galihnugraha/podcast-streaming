package com.proyekakhir.podcaststreaming.service

import com.proyekakhir.podcaststreaming.domain.dto.request.ReqLoginDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResEncodeJwtDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResMessageDto

interface LoginService {
    fun login(req:ReqLoginDto): ResMessageDto<ResEncodeJwtDto>
}