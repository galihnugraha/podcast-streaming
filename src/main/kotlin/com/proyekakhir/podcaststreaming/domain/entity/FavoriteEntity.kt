package com.proyekakhir.podcaststreaming.domain.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "mst_favorite")
data class FavoriteEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_favorite")
        val idFavorite:Int? = null,

        @ManyToOne
        @JoinColumn(name = "id_podcast", referencedColumnName = "id_podcast")
        var idPodcast: PodcastEntity? = null,

        @Column(name = "user_added")
        var userAdded:String? = null,

        @Column(name = "dt_added")
        val dtAdded: LocalDateTime? = null,

        @Column(name = "dt_updated")
        var dtUpdated: LocalDateTime? = null,
)
