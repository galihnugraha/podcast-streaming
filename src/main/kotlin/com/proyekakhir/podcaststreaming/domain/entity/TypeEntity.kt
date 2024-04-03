package com.proyekakhir.podcaststreaming.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "mst_type")
data class TypeEntity(
        @Id
        @Column(name = "id_type")
        val idType:String? = null,

        @Column(name = "type")
        var type:String? = null,
)
