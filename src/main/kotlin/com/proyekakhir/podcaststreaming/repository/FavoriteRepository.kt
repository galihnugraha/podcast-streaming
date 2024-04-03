package com.proyekakhir.podcaststreaming.repository

import com.proyekakhir.podcaststreaming.domain.entity.FavoriteEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FavoriteRepository: JpaRepository<FavoriteEntity, Int> {
    fun findAllByUserAdded(userAdded:String?): List<FavoriteEntity>
}