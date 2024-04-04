package com.proyekakhir.podcaststreaming.repository

import com.proyekakhir.podcaststreaming.domain.entity.GenreEntity
import com.proyekakhir.podcaststreaming.domain.entity.PodcastEntity
import com.proyekakhir.podcaststreaming.domain.entity.TypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PodcastRepository: JpaRepository<PodcastEntity, Int> {
    fun findAllByIdGenre(genre: GenreEntity): List<PodcastEntity>

    fun findAllByIdType(type: TypeEntity): List<PodcastEntity>

    @Query("SELECT p FROM PodcastEntity p WHERE p.title LIKE %?1% OR p.idGenre.genre LIKE %?1%")
    fun findByGenreAndTitle(s: String): List<PodcastEntity>
}