package com.proyekakhir.podcaststreaming.repository

import com.proyekakhir.podcaststreaming.domain.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Int> {
    fun findByUsername(username:String?):UserEntity?

    fun findByEmail(email:String?):UserEntity?
}