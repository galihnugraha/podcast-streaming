package com.proyekakhir.podcaststreaming.service.impl

import com.proyekakhir.podcaststreaming.domain.dto.request.ReqFavoriteDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResFavoriteDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResMessageDto
import com.proyekakhir.podcaststreaming.domain.entity.FavoriteEntity
import com.proyekakhir.podcaststreaming.exception.DataNotFound
import com.proyekakhir.podcaststreaming.repository.FavoriteRepository
import com.proyekakhir.podcaststreaming.repository.PodcastRepository
import com.proyekakhir.podcaststreaming.service.FavoriteService
import com.proyekakhir.podcaststreaming.utils.JwtGenerator
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class FavoriteServiceImpl(
        val favoriteRepository: FavoriteRepository,
        val podcastRepository: PodcastRepository
): FavoriteService {
    override fun insert(token: String, req: ReqFavoriteDto): ResMessageDto<ResFavoriteDto> {
        val claim = JwtGenerator().decodeJwt(token)

        val podcast = podcastRepository.findById(req.idPodcast!!.toInt())
                .orElseThrow{ DataNotFound("Podcast Not Found") }

        if (claim["type"].toString() == "Free") {
            if (claim["type"].toString() != podcast.idType!!.type)
                throw DataNotFound("Podcast Not Found")
        }

        val now = LocalDateTime.now()

        val insert = FavoriteEntity(
                idPodcast = podcast,
                userAdded = claim["username"].toString(),
                dtAdded = now,
                dtUpdated = now
        )
        val savedFavorite = favoriteRepository.save(insert)
        val response = ResFavoriteDto(
                idFavorite = savedFavorite.idFavorite!!,
                titlePodcast = savedFavorite.idPodcast!!.title,
                userAdded = savedFavorite.userAdded!!,
                dtAdded = savedFavorite.dtAdded!!,
                dtUpdated = savedFavorite.dtUpdated!!
        )
        return ResMessageDto(data = response)
    }

    override fun list(token: String): ResMessageDto<List<ResFavoriteDto>> {
        val claim = JwtGenerator().decodeJwt(token)

        val favorites = favoriteRepository.findAllByUserAdded(claim["username"].toString())

        if (favorites.isEmpty())
            return ResMessageDto(message = "No favorites found for this user")

        val response = arrayListOf<ResFavoriteDto>()
        for (favorite in favorites){
            val data = ResFavoriteDto(
                    idFavorite = favorite.idFavorite!!,
                    titlePodcast = favorite.idPodcast!!.title,
                    userAdded = favorite.userAdded!!,
                    dtAdded = favorite.dtAdded!!,
                    dtUpdated = favorite.dtUpdated!!
            )
            response.add(data)
        }
        return ResMessageDto(data = response)
    }

    override fun detail(token: String, id: Int): ResMessageDto<ResFavoriteDto> {
        val claim = JwtGenerator().decodeJwt(token)

        val favoriteFind = favoriteRepository.findById(id).orElseThrow{ DataNotFound("Favorite Content Not Found") }

        if (claim["username"].toString() != favoriteFind.userAdded)
            throw DataNotFound("Favorite Content Not Found")

        val response = ResFavoriteDto(
                idFavorite = favoriteFind.idFavorite!!,
                titlePodcast = favoriteFind.idPodcast!!.title,
                userAdded = favoriteFind.userAdded!!,
                dtAdded = favoriteFind.dtAdded!!,
                dtUpdated = favoriteFind.dtUpdated!!
        )
        return ResMessageDto(data = response)
    }

    override fun update(token: String, id: Int, req: ReqFavoriteDto): ResMessageDto<ResFavoriteDto> {
        val claim = JwtGenerator().decodeJwt(token)

        val favoriteFind = favoriteRepository.findById(id).orElseThrow{ DataNotFound("Favorite Content Not Found") }

        if (claim["username"].toString() != favoriteFind.userAdded)
            throw DataNotFound("Favorite Content Not Found")

        val podcast = podcastRepository.findById(req.idPodcast!!.toInt())
                .orElseThrow{ DataNotFound("Podcast Not Found") }

        val now = LocalDateTime.now()

        favoriteFind.idPodcast = podcast
        favoriteFind.dtUpdated = now

        val updateFavorite = favoriteRepository.save(favoriteFind)
        val response = ResFavoriteDto(
                idFavorite = updateFavorite.idFavorite!!,
                titlePodcast = updateFavorite.idPodcast!!.title,
                userAdded = updateFavorite.userAdded!!,
                dtAdded = updateFavorite.dtAdded!!,
                dtUpdated = updateFavorite.dtUpdated!!
        )
        return ResMessageDto(data = response)
    }

    override fun delete(token: String, id: Int): ResMessageDto<String> {
        val claim = JwtGenerator().decodeJwt(token)
        val checkId = favoriteRepository.findById(id)

        if(!checkId.isPresent)
            throw DataNotFound("Favorite Content Not Found")

        if (claim["username"].toString() != checkId.get().userAdded)
            throw DataNotFound("Favorite Content Not Found")

        favoriteRepository.deleteById(id)

        return ResMessageDto()
    }
}