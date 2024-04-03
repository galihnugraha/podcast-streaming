package com.proyekakhir.podcaststreaming.domain.entity

import jakarta.persistence.*

@Entity
@Table(name = "mst_user")
data class UserEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_user")
        val idUser:Int? = null,

        @Column(name = "username")
        var username:String? = null,

        @Column(name = "email")
        var email:String? = null,

        @Column(name = "password")
        var password:String? = null,

        @ManyToOne
        @JoinColumn(name = "id_type", referencedColumnName = "id_type")
        var idType: TypeEntity? = null,
)
