package com.proyekakhir.podcaststreaming.service.impl

import com.proyekakhir.podcaststreaming.domain.dto.request.ReqPodcastDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResMessageDto
import com.proyekakhir.podcaststreaming.domain.dto.response.ResPodcastDto
import com.proyekakhir.podcaststreaming.domain.entity.PodcastEntity
import com.proyekakhir.podcaststreaming.exception.DataNotFound
import com.proyekakhir.podcaststreaming.repository.GenreRepository
import com.proyekakhir.podcaststreaming.repository.PodcastRepository
import com.proyekakhir.podcaststreaming.repository.TypeRepository
import com.proyekakhir.podcaststreaming.service.PodcastService
import com.proyekakhir.podcaststreaming.utils.JwtGenerator
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PodcastServiceImpl(
        val podcastRepository: PodcastRepository,
        val typeRepository: TypeRepository,
        val genreRepository: GenreRepository
): PodcastService {
    override fun insert(req: ReqPodcastDto): ResMessageDto<ResPodcastDto> {
        val type = if (req.idType == null) {
            typeRepository.findById("T001")
                    .orElseThrow{ DataNotFound("Type Not Found") }
        } else {
            typeRepository.findById(req.idType.toString())
                    .orElseThrow{ DataNotFound("Type Not Found") }
        }

        val genre = genreRepository.findById(req.idGenre!!.toInt())
                .orElseThrow{ DataNotFound("Genre Not Found") }

        val now = LocalDateTime.now()

        val insert = PodcastEntity(
                title = req.title,
                author = req.author,
                duration = req.duration,
                idType = type,
                idGenre = genre,
                dtAdded = now,
                dtUpdated = now
        )
        val savedPodcast = podcastRepository.save(insert)
        val response = ResPodcastDto(
                idPodcast = savedPodcast.idPodcast!!,
                title = savedPodcast.title!!,
                author = savedPodcast.author!!,
                duration = savedPodcast.duration!!,
                type = savedPodcast.idType!!.type,
                genre = savedPodcast.idGenre!!.genre,
                dtAdded = savedPodcast.dtAdded!!,
                dtUpdated = savedPodcast.dtUpdated!!
        )
        return ResMessageDto(data = response)
    }

    override fun list(token: String): ResMessageDto<List<ResPodcastDto>> {
        val claim = JwtGenerator().decodeJwt(token)

        val podcasts:List<PodcastEntity>

        if (claim["type"].toString() == "Free") {
            val type = typeRepository.findById("T001").orElseThrow{ DataNotFound("Type Not Found") }
            podcasts = podcastRepository.findAllByIdType(type)
        } else {
            podcasts = podcastRepository.findAll()
        }

        val response = arrayListOf<ResPodcastDto>()
        for (podcast in podcasts){
            val data = ResPodcastDto(
                    idPodcast = podcast.idPodcast!!,
                    title = podcast.title!!,
                    author = podcast.author!!,
                    duration = podcast.duration!!,
                    type = podcast.idType!!.type,
                    genre = podcast.idGenre!!.genre,
                    dtAdded = podcast.dtAdded!!,
                    dtUpdated = podcast.dtUpdated!!
            )
            response.add(data)
        }
        return ResMessageDto(data = response)
    }

    override fun detail(token: String, id: Int): ResMessageDto<ResPodcastDto> {
        val claim = JwtGenerator().decodeJwt(token)

        val podcastFind = podcastRepository.findById(id).orElseThrow{ DataNotFound("Podcast Not Found") }

        if (claim["type"].toString() == "Free" && podcastFind.idType!!.type == "Premium")
            throw DataNotFound("Podcast Not Found")

        val response = ResPodcastDto(
                idPodcast = podcastFind.idPodcast!!,
                title = podcastFind.title!!,
                author = podcastFind.author!!,
                duration = podcastFind.duration!!,
                type = podcastFind.idType!!.type,
                genre = podcastFind.idGenre!!.genre,
                dtAdded = podcastFind.dtAdded!!,
                dtUpdated = podcastFind.dtUpdated!!
        )
        return ResMessageDto(data = response)
    }

    override fun update(id: Int, req: ReqPodcastDto): ResMessageDto<ResPodcastDto> {
        val podcastFind = podcastRepository.findById(id).orElseThrow{ DataNotFound("Podcast Not Found") }
        val type = if (req.idType == null) {
            typeRepository.findById("T001")
                    .orElseThrow{ DataNotFound("Type Not Found") }
        } else {
            typeRepository.findById(req.idType.toString())
                    .orElseThrow{ DataNotFound("Type Not Found") }
        }

        val genre = genreRepository.findById(req.idGenre!!.toInt())
                .orElseThrow{ DataNotFound("Genre Not Found") }

        val now = LocalDateTime.now()

        podcastFind.title = req.title
        podcastFind.author = req.author
        podcastFind.duration = req.duration
        podcastFind.idType = type
        podcastFind.idGenre = genre
        podcastFind.dtUpdated = now

        val updatePodcast = podcastRepository.save(podcastFind)
        val response = ResPodcastDto(
                idPodcast = updatePodcast.idPodcast!!,
                title = updatePodcast.title!!,
                author = updatePodcast.author!!,
                duration = updatePodcast.duration!!,
                type = updatePodcast.idType!!.type,
                genre = updatePodcast.idGenre!!.genre,
                dtAdded = updatePodcast.dtAdded!!,
                dtUpdated = updatePodcast.dtUpdated!!
        )
        return ResMessageDto(data = response)
    }

    override fun delete(id: Int): ResMessageDto<String> {
        val checkId = podcastRepository.findById(id)

        if(!checkId.isPresent)
            throw DataNotFound("Podcast Not Found")

        podcastRepository.deleteById(id)

        return ResMessageDto()
    }

    override fun search(token: String, s: String): ResMessageDto<List<ResPodcastDto>> {
        val claim = JwtGenerator().decodeJwt(token)

//        val genreName = genreRepository.findByGenre(s)
//
//        val podcasts = if (genreName != null)
//            podcastRepository.findAllByIdGenre(genreName)
//        else
//            podcastRepository.findByTitle(s)

        val podcasts = podcastRepository.findByGenreAndTitle(s)

        val response = arrayListOf<ResPodcastDto>()
        for (podcast in podcasts){
            if (claim["type"].toString() == "Free" && podcast.idType!!.type == "Premium")
                continue

            val data = ResPodcastDto(
                    idPodcast = podcast.idPodcast!!,
                    title = podcast.title!!,
                    author = podcast.author!!,
                    duration = podcast.duration!!,
                    type = podcast.idType!!.type,
                    genre = podcast.idGenre!!.genre,
                    dtAdded = podcast.dtAdded!!,
                    dtUpdated = podcast.dtUpdated!!
            )
            response.add(data)
        }
        return if (response.isEmpty())
            ResMessageDto(message = "No Podcast Found")
        else
            ResMessageDto(data = response)
    }
}