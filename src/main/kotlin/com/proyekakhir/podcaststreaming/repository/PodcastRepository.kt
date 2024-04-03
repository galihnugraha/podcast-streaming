package com.proyekakhir.podcaststreaming.repository

import com.proyekakhir.podcaststreaming.domain.entity.GenreEntity
import com.proyekakhir.podcaststreaming.domain.entity.PodcastEntity
import com.proyekakhir.podcaststreaming.domain.entity.TypeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PodcastRepository: JpaRepository<PodcastEntity, Int> {
    fun findByTitle(title:String?): List<PodcastEntity>

    fun findAllByIdType(type: TypeEntity): List<PodcastEntity>

    fun findAllByIdGenre(genre: GenreEntity): List<PodcastEntity>
}