package com.proyekakhir.podcaststreaming.service

import com.proyekakhir.podcaststreaming.domain.dto.request.ReqPodcastDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResMessageDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResPodcastDto

interface PodcastService {
    fun insert(req: ReqPodcastDto): ResMessageDto<ResPodcastDto>

    fun list(token: String): ResMessageDto<List<ResPodcastDto>>

    fun detail(token: String, id: Int): ResMessageDto<ResPodcastDto>

    fun update(id: Int, req: ReqPodcastDto): ResMessageDto<ResPodcastDto>

    fun delete(id: Int): ResMessageDto<String>

    fun search(token: String, s: String): ResMessageDto<List<ResPodcastDto>>
}