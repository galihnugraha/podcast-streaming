package com.proyekakhir.podcaststreaming.repository

import com.proyekakhir.podcaststreaming.domain.entity.GenreEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GenreRepository: JpaRepository<GenreEntity, Int> {
    fun findByGenre(genre:String?):GenreEntity?
}