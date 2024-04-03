package com.proyekakhir.podcaststreaming.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "mst_podcast")
data class PodcastEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_podcast")
        val idPodcast:Int? = null,

        @Column(name = "title")
        var title:String? = null,

        @Column(name = "author")
        var author:String? = null,

        @Column(name = "duration")
        var duration:Int? = null,

        @ManyToOne
        @JoinColumn(name = "id_type", referencedColumnName = "id_type")
        var idType: TypeEntity? = null,

        @ManyToOne
        @JoinColumn(name = "id_genre", referencedColumnName = "id_genre")
        var idGenre: GenreEntity? = null,

        @Column(name = "dt_added")
        val dtAdded:LocalDateTime? = null,

        @Column(name = "dt_updated")
        var dtUpdated:LocalDateTime? = null
)
