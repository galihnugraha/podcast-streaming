package com.proyekakhir.podcaststreaming.repository

import com.proyekakhir.podcaststreaming.domain.entity.TypeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TypeRepository: JpaRepository<TypeEntity, String> {
    fun findByIdType(type:String?):TypeEntity?

    fun findByType(type:String?):TypeEntity?
}