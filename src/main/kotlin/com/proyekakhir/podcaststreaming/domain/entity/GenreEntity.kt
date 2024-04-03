package com.proyekakhir.podcaststreaming.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "mst_genre")
data class GenreEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_genre")
        val idGenre:Int? = null,

        @Column(name = "genre")
        var genre:String? = null,
)
